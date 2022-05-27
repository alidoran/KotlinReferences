package alidoran.android_fundamental.broadcast

import alidoran.android_fundamental.services.ScreenOnOffService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startForegroundService

class BroadcastOnOffReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d("AliDoran", "AliDoran:ACTION_SCREEN_OFF")
        } else if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("AliDoran", "AliDoran:ACTION_SCREEN_ON")
            val i = Intent(context, ScreenOnOffService::class.java)
            startForegroundService(context, i)
        }
    }
}