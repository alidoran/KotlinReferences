package alidoran.android.location

import alidoran.android.databinding.ActivityLocationBinding
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.*
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.launch


class LocationActivity : AppCompatActivity() {

    lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnRequestFinePermission.setOnClickListener { checkLocationPermission(true) }
        btnRequestCoarsePermission.setOnClickListener { checkLocationPermission(false) }
        btnGetPermissionStatus.setOnClickListener {
            val finePermission = getLocationFinePermission(true)
            val coarsePermission = getLocationFinePermission(false)
            val result = "Fine: $finePermission \n Coarse: $coarsePermission"
            txtLocationPermission.text = result
        }
        btnGetCurrentLocation.setOnClickListener { getCurrentLocation(true) }
    }

    private fun getLocationFinePermission(isFine: Boolean) =
        ActivityCompat.checkSelfPermission(
            this,
            if (isFine)Manifest.permission.ACCESS_FINE_LOCATION
            else Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun checkLocationPermission(isFine: Boolean) {
        locationPermissionRequest.launch(
            arrayOf(
                if (isFine) Manifest.permission.ACCESS_FINE_LOCATION
                else Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(needFineLocation: Boolean) {
        lifecycleScope.launch {
            val priority =
                if (needFineLocation) PRIORITY_HIGH_ACCURACY
                else PRIORITY_BALANCED_POWER_ACCURACY
            if (getLocationFinePermission(needFineLocation))
                LocationServices
                    .getFusedLocationProviderClient(applicationContext)
                    .getCurrentLocation(priority, CancellationTokenSource().token)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this@LocationActivity,
                            "${it.result.longitude}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
        }
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                Log.d("LocationPermission", "FINE_LOCATION is granted")
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                Log.d("LocationPermission", "COARSE_LOCATION is granted")
            }

            else -> {
                Log.d("LocationPermission", "LOCATION isn't granted")
            }
        }
    }
}