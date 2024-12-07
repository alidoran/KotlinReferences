package alidoran.android.sensors.ui

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import alidoran.android.sensors.navigation.SensorRoutes
import alidoran.android.sensors.step_count.StepCountScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

lateinit var onPermissionListener: (Boolean) -> Unit

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
                                        .launch(android.Manifest.permission.ACTIVITY_RECOGNITION)
                                    onPermissionListener = { isGranted ->
                                        if (isGranted) {
                                            navController.navigate(SensorRoutes.StepCount)
                                        }
                                    }
                                }
                            )
                        }
                        composable<SensorRoutes.StepCount> {
                            StepCountScreen(
                                onBackClick = { navController.navigateUp() },
                            )
                        }
                    }
                }
            }
        }
    }
}