package alidoran.android.permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityPermissionsBinding
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

class PermissionsActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent(binding)
    }

    private fun initEvent(binding: ActivityPermissionsBinding) = with(binding) {
        btnOverlay.setOnClickListener {
            checkOverlayPermission()
        }

        btnLocation.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        }
    }

    private fun checkLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}