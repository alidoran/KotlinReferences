package alidoran.third_party.firebase.fcm_push_notification.services

import alidoran.third_party.R
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.START_BG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.START_FG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.STOP_BG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.STOP_FG_SERVICE_26
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.*


/*
Manifest
<service android:name=".services.BackgroundServiceSample"/>
 */

class ServiceUpper26 : Service() {
    private var stopTimer = false

    object ActionType{
        const val START_BG_SERVICE_26 = "startBgService26"
        const val START_FG_SERVICE_26 = "startFgService26"
        const val STOP_BG_SERVICE_26= "stopBgService26"
        const val STOP_FG_SERVICE_26= "stopFgService26"
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
        Toast.makeText(applicationContext, "The service is :$action", Toast.LENGTH_LONG).show()
        when (action) {
            START_BG_SERVICE_26 -> {

            }
            STOP_FG_SERVICE_26 -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
            START_FG_SERVICE_26 -> {
                startForeground()
            }
            STOP_BG_SERVICE_26 -> {
                startForeground()
                stopSelf()
            }
        }

            return super.onStartCommand(intent, flags, startId)
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


