package android.services

import com.example.android.databinding.ActivityServicesBinding
import android.app.ActivityManager
import android.app.Service
import android.content.Intent
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ServicesActivity : AppCompatActivity() {
    lateinit var binding: ActivityServicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatus()

        binding.btnStartBackgroundService.setOnClickListener {
            if (!isServiceRunning(BackgroundServiceSample())) {
                val intent = Intent(this, BackgroundServiceSample::class.java)
                startService(intent)
            }
            setStatus()
        }

        binding.btnStartForegroundService.setOnClickListener {
            if (!isServiceRunning(ForegroundServiceSample())) {
                val intent = Intent(this, ForegroundServiceSample()::class.java)
                startForegroundService(intent)
            }
            setStatus()
        }

        binding.btnStopBackgroundService.setOnClickListener {
            if (isServiceRunning(BackgroundServiceSample()))
                stopService(Intent(this, BackgroundServiceSample::class.java))
            setStatus()
        }

        binding.btnStopForegroundService.setOnClickListener {
            if (isServiceRunning(ForegroundServiceSample()))
                stopService(Intent(this, ForegroundServiceSample()::class.java))
            setStatus()
        }

        binding.btnStartOnOffScreenService.setOnClickListener {
            if (!isServiceRunning(ScreenOnOffService())) {
                val intent = Intent(this, ScreenOnOffService()::class.java)
                startForegroundService(intent)
            }
            setStatus()
        }

        binding.btnStopOnOffScreenService.setOnClickListener {
            if (isServiceRunning(ScreenOnOffService()))
                stopService(Intent(this, ScreenOnOffService::class.java))
            setStatus()
        }

        binding.btnStartTransparentService.setOnClickListener{
            if (!isServiceRunning(TrampolineService())) {
                val intent = Intent(this, TrampolineService()::class.java)
                intent.putExtra("notification", "123")
                startForegroundService(intent)
            }
            setStatus()
        }

        binding.btnStopTransparentService.setOnClickListener{
            if (isServiceRunning(TrampolineService()))
                stopService(Intent(this, TrampolineService::class.java))
            setStatus()
        }
    }

    private fun isServiceRunning(serviceClass: Service): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass::class.java.name.equals(service.service.className))
                return true
        }
        return false
    }

    private fun setStatus() {
        val isBackgroundRun = isServiceRunning(BackgroundServiceSample())
        val isForegroundRun = isServiceRunning(ForegroundServiceSample())
        val isOnOffScreenRun = isServiceRunning(ScreenOnOffService())
        val isTransparentRun = isServiceRunning(TrampolineService())
        binding.txtBackgroundServicesStatus.text =
            if (isBackgroundRun) "Background service is running ..."
            else "Background is stopped"
        binding.txtBackgroundServicesStatus.setTextColor(
            if (isBackgroundRun) GREEN
            else RED
        )
        binding.txtForegroundServiceStatus.text =
            if (isForegroundRun) "Foreground service is running ..."
            else "Foreground service is stopped"
        binding.txtForegroundServiceStatus.setTextColor(
            if (isForegroundRun) GREEN
            else RED
        )
        binding.txtOnOffScreenServiceStatus.text =
            if (isOnOffScreenRun) "OnOff screen service is running ..."
            else "OnOff screen service is stopped"
        binding.txtOnOffScreenServiceStatus.setTextColor(
            if (isOnOffScreenRun) GREEN
            else RED
        )
        binding.txtTransparentStatus.text =
            if (isTransparentRun) "Transparent service is running ..."
            else "Transparent screen service is stopped"
        binding.txtTransparentStatus.setTextColor(
            if (isTransparentRun) GREEN
            else RED
        )
    }
}