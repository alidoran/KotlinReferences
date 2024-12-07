package alidoran.android.sensors.ui

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import alidoran.android.sensors.navigation.SensorRoutes
import alidoran.android.sensors.recoring_api.RecordingApiScreen
import alidoran.android.sensors.step_count.StepCountScreen
import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtilLight.isGooglePlayServicesAvailable
import com.google.android.gms.fitness.FitnessLocal
import com.google.android.gms.fitness.LocalRecordingClient
import com.google.android.gms.fitness.data.LocalDataSet
import com.google.android.gms.fitness.data.LocalDataType
import com.google.android.gms.fitness.request.LocalDataReadRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

lateinit var onPermissionListener: (Boolean) -> Unit
private val dataFlow = MutableStateFlow("Initial Data")

class SensorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val registerForActivityResult = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionListener.invoke(isGranted)
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                Column {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = SensorRoutes.SensorsHome
                    ) {
                        composable<SensorRoutes.SensorsHome> {

                            SensorsHomeScreen(
                                onStepCounterSensorClick = {
                                    registerForActivityResult
                                        .launch(Manifest.permission.ACTIVITY_RECOGNITION)
                                    onPermissionListener = { isGranted ->
                                        if (isGranted) {
                                            navController.navigate(SensorRoutes.StepCount)
                                        }
                                    }
                                },
                                onRecordingApiClick = {
                                    navController.navigate(SensorRoutes.RecordingApi)
                                },
                            )
                        }
                        composable<SensorRoutes.StepCount> {
                            StepCountScreen(
                                onBackClick = { navController.navigateUp() },
                            )
                        }
                        composable<SensorRoutes.RecordingApi> { backStackEntry ->
                            val scope = rememberCoroutineScope()
                            RecordingApiScreen(onCountReadClick = {
                                scope.launch {
                                    while (true) {
                                        delay(1000)
                                        recordingApi()
                                    }
                                }
                                dataFlow
                            },
                                onStopClick = { dataFlow.value = "" })
                        }
                    }
                }
            }
        }
    }

    private fun recordingApi() {
        val hasMinPlayServices =
            isGooglePlayServicesAvailable(
                this,
                LocalRecordingClient.LOCAL_RECORDING_CLIENT_MIN_VERSION_CODE
            )

        if (hasMinPlayServices != ConnectionResult.SUCCESS) {
            // Prompt user to update their device's Google Play services app and return
        }
        val localRecordingClient = FitnessLocal.getLocalRecordingClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        localRecordingClient.subscribe(LocalDataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener {
                Log.i(TAG, "Successfully subscribed!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "There was a problem subscribing.", e)
            }

        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
        val readRequest =
            LocalDataReadRequest.Builder()
                .aggregate(LocalDataType.TYPE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()

        localRecordingClient.readData(readRequest).addOnSuccessListener { response ->
            for (dataSet in response.buckets.flatMap { it.dataSets }) {
                CoroutineScope(Dispatchers.Default).launch {
                    dataFlow.emit(dumpDataSet(dataSet))
                }
            }
        }
            .addOnFailureListener { e ->
                Log.w(TAG, "There was an error reading data", e)
            }
    }

    private fun dumpDataSet(dataSet: LocalDataSet): String {
        Log.i(TAG, "Data returned for Data type: ${dataSet.dataType.name}")
        val report = StringBuilder()
        for (dp in dataSet.dataPoints) {
            Log.i(TAG, "Data point:")
            report.append("Data point:")
            Log.i(TAG, "\tType: ${dp.dataType.name}")
            report.append("\tType: ${dp.dataType.name}")
            Log.i(TAG, "\tStart: ${dp.getStartTime(TimeUnit.HOURS)}")
            report.append("\tStart: ${dp.getStartTime(TimeUnit.HOURS)}")
            Log.i(TAG, "\tEnd: ${dp.getEndTime(TimeUnit.HOURS)}")
            report.append("\tEnd: ${dp.getEndTime(TimeUnit.HOURS)}")
            for (field in dp.dataType.fields) {
                Log.i(
                    TAG,
                    "\tLocalField: ${field.name} LocalValue: ${dp.getValue(field)}"
                )
                report.append(
                    "	\tLocalField: ${field.name} LocalValue: ${
                        dp.getValue(
                            field
                        )
                    }"
                )
            }
        }
        return report.toString()
    }
}