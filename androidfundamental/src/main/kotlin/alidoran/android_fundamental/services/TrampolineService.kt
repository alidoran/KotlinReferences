package alidoran.android_fundamental.services

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.text.format.DateUtils

open class TrampolineService: Service(){
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val channelId = "ScreenOnOff channelId"
        val notificationChannel =
            NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_UNSPECIFIED)
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            notificationChannel
        )
        val pendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        val notification = Notification
            .Builder(this, channelId)
            .setWhen(DateUtils.SECOND_IN_MILLIS*20)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        startForeground(1002, notification)
        return super.onStartCommand(intent, flags, startId)
    }

}