package alidoran.third_party.firebase.fcm_push_notification.services

import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.START_BG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.START_FG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.STOP_BG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.STOP_FG_SERVICE
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ServiceLower26 : Service() {
    private var stopTimer = false

    object ActionType {
        const val START_BG_SERVICE = "startBgService"
        const val START_FG_SERVICE = "startFgService"
        const val STOP_BG_SERVICE = "stopBgService"
        const val STOP_FG_SERVICE = "stopFgService"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopTimer = true
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent!!.getStringExtra("ActionType")
        Toast.makeText(applicationContext, "The service is :$action", Toast.LENGTH_LONG).show()
        when (action) {
            START_BG_SERVICE -> {

            }

            STOP_FG_SERVICE -> {
                stopForeground(true)
                stopSelf()
            }

            START_FG_SERVICE -> {
                startForeground()
            }

            STOP_BG_SERVICE -> {
                startForeground()
                stopSelf()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForeground() {
        val builder = NotificationCompat.Builder(this)
            .setContentTitle("")
            .setContentText("")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        val notification: Notification = builder.build()
        startForeground(1, notification)
    }
}