package project.services

import alidoran.android_fundamental.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


/*
Manifest
<service android:name=".services.ForegroundServiceSample"/>
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
 */
class ForegroundServiceSample : Service() {

    private var stopTimer = false

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopTimer = true
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        printTimeSecondly()

        val channelId = "Foreground channelId"
        val notificationChannel =
            NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH)
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            notificationChannel
        )

        val notification = Notification
            .Builder(this, channelId)
            .setContentText("Foreground service is running")
            .setContentTitle("Foreground service is enable")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        startForeground(1001, notification)

        return super.onStartCommand(intent, flags, startId)
    }

    private fun printTimeSecondly() {
        if (!stopTimer)
            Timer().schedule(TimeUnit.SECONDS.toMillis(1)) {
                Log.d("Foreground service", "AliDoranTime = ${Calendar.getInstance().time}")
                printTimeSecondly()
            }
    }


}