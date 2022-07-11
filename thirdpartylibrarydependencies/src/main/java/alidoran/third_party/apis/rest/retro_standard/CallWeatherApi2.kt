package alidoran.third_party.apis.rest.retro_standard

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CallWeatherApi2{
    fun weatherNow(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = getWeatherService2().getWeatherApi2(q = "Tehran")
            if (response.isSuccessful)
                Log.d(TAG, "makeApiCall: ${response.body()!!.location}")
            else
                Log.d(TAG, "makeApiCall: ${response.errorBody()}")
        }
    }
}