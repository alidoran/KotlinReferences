package android.test

import android.util.Log

class Logs {
    fun normalLog(boolean: Boolean){
        if (boolean)
            Log.i("normalLog", "normalLogTrue")
        else
            Log.i("normalLog", "normalLogFalse")
    }
}