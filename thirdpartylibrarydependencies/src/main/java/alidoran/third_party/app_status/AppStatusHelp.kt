package alidoran.third_party.app_status

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class AppStatusHelp(val context: Context) {
    private val APP_STATUS = "APP_STATUS"
    private val sharedPreferences =
        context.getSharedPreferences("mPreferenceName", AppCompatActivity.MODE_PRIVATE)

    fun getIsAppInBackground(): Boolean {
        return sharedPreferences.getBoolean(APP_STATUS, false)
    }

    fun setIsAppInBackground(status: Boolean) {
        sharedPreferences.edit().apply { putBoolean(APP_STATUS, true)}.apply()
    }
}