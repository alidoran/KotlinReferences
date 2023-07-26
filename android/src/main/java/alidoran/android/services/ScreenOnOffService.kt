package alidoran.android.services

import alidoran.android.R
import alidoran.android.broadcast.BroadcastOnOffReceiver
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.content.Context
import java.time.LocalDateTime

class ScreenOnOffService : Service() {
    private var broadcastOnOffReceiver: BroadcastOnOffReceiver? = null
    

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        registerScreenOnOffService()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startNotification(this)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startNotification(context: Context) {
        val channelId = "ScreenOnOff channelId"
        val notificationChannel =
            NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(
            notificationChannel
        )

        val notification = Notification
            .Builder(context, channelId)
            .setContentText("ScreenOnOff service is running")
            .setContentTitle("ScreenOnOff service is enable")
            .setSmallIcon(getChangedIconEveryTenSec())
            .build()

        startForeground(1002, notification)
    }

    private fun getChangedIconEveryTenSec(): Int {
        val sec = LocalDateTime.now().second
        return if ((sec / 10) % 2 == 0)
            R.drawable.baseline_thumb_up_black_24dp
        else
            R.drawable.baseline_thumb_down_black_24dp
    }

    override fun onDestroy() {
        unRegisterScreenOnOffService()
        super.onDestroy()
    }

    private fun registerScreenOnOffService() {
        broadcastOnOffReceiver = BroadcastOnOffReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_SCREEN_ON)
        registerReceiver(broadcastOnOffReceiver, filter)
    }

    private fun unRegisterScreenOnOffService() {
        try {
            if (broadcastOnOffReceiver != null) {
                unregisterReceiver(broadcastOnOffReceiver)
            }
        } catch (_: IllegalArgumentException) {
        }
    }
}