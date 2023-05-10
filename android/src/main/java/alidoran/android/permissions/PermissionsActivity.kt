package alidoran.android.permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityPermissionsBinding
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

class PermissionsActivity : AppCompatActivity() {
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
    }

    private fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivity(intent)
            }
        }
    }
}