package alidoran.android_fundamental.broadcast

import android.app.ActivityManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.*
import alidoran.android_fundamental.services.ForegroundServiceSample


class BroadcastReceiverSample : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("AliDoran", "AliDoran:ACTION_BOOT_COMPLETED")
            startServiceFromBroadcast(context)
        }
    }

    private fun isServiceRunning(serviceClass: Service, context: Context): Boolean {
        Log.d("AliDoran", "AliDoran:isServiceRunningMethodCalled")
        val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            Log.d("AliDoran", service.service.className)
            if (serviceClass::class.java.name.equals(service.service.className))
                Log.d("AliDoran", "AliDoran:isServiceRunning:true")
            return true
        }
        Log.d("AliDoran", "AliDoran:isServiceRunning:false")
        return false
    }

    private fun startServiceFromBroadcast(context: Context?) {
        if (!isServiceRunning(ForegroundServiceSample(), context!!)) {
            Log.d("AliDoran", "AliDoran:startServiceFromBroadcast")
            val intent = Intent(context, ForegroundServiceSample()::class.java)
            startForegroundService(context, intent)
        }
    }
}