package alidoran.third_party.firebase.fcm_push_notification.second_service

import alidoran.third_party.R
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.START_FOREGROUND
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.START_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.STOP_SERVICE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


/*
Manifest
<service android:name=".services.BackgroundServiceSample"/>
 */

class BackgroundSecondService : Service() {
    private var stopTimer = false

    object ActionType{
        const val START_SERVICE = "startService"
        const val STOP_SERVICE = "stopService"
        const val START_FOREGROUND = "startForeground"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopTimer = true
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent!!.getStringExtra("ActionType")
        if (action == START_SERVICE){
            printTimeSecondly()
        }
        else if (action == STOP_SERVICE) {
            stopForeground(true)
            stopSelf()
        }
        else if (action == START_FOREGROUND){
            startForeground()
        }
            return super.onStartCommand(intent, flags, startId)
    }

    private fun printTimeSecondly() {
        if (!stopTimer) {
            Timer().schedule(TimeUnit.SECONDS.toMillis(1)) {
                Log.d("Background service", ("AliDoranTime = ${Calendar.getInstance().time}"))
                printTimeSecondly()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startForeground() {
        val intent = Intent(this, ActivityFcmPushNotificationBinding::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = this.getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My Title")
            .setContentText("body")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setOngoing(true)

        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = notificationBuilder.build()

// Notification ID cannot be 0.
        startForeground(2, notification)
    }
}


