package project.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


/*
Manifest
<service android:name=".services.BackgroundServiceSample"/>
 */

class BackgroundServiceSample:Service() {
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
        return super.onStartCommand(intent, flags, startId)
    }

    private fun printTimeSecondly(){
        if (!stopTimer)
        Timer().schedule(TimeUnit.SECONDS.toMillis(1)){
            Log.d("Background service", ("AliDoranTime = ${Calendar.getInstance().time}"))
            printTimeSecondly()
        }
    }
}


