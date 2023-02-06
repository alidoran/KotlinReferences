package alidoran.third_party.firebase.fcm_push_notification

import android.app.ActivityManager
import android.content.Context
import android.os.AsyncTask

@Suppress("DEPRECATED_IDENTITY_EQUALS")
internal class ForegroundCheckTask :
    AsyncTask<Context?, Void?, Boolean?>() {

    private fun isAppOnForeground(context: Context): Boolean {
        val activityManager: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses: List<ActivityManager.RunningAppProcessInfo> =
            activityManager.runningAppProcesses
                ?: return false
        val packageName: String = context.packageName
        for (appProcess in appProcesses) {
            if (appProcess.importance === ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                && appProcess.processName.equals(
                    packageName
                )
            ) {
                return true
            }
        }
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Context?): Boolean? {
        val context=  params[0]?.getApplicationContext()
        return isAppOnForeground(context!!)
    }
}