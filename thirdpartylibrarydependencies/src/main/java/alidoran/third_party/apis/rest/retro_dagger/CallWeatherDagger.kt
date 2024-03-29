package alidoran.third_party.apis.rest.retro_dagger


import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CallWeatherDagger {
    var mService = DaggerWeatherComponent.create().getWeatherApi()

    init {
        makeApiCall()
    }


    private fun makeApiCall() {
        runBlocking {
            val response = mService.getWeatherApi3(q = "Tehran")
            if (response.isSuccessful)
                Log.d(TAG, "makeApiCall: ${response.body()!!.current}")
            else
                Log.d(TAG, "makeApiCall: ${response.errorBody()}")
        }
    }

}