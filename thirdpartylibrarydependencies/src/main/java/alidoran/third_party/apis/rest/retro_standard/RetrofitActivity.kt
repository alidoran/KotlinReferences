package alidoran.third_party.apis.rest.retro_standard

import alidoran.third_party.apis.rest.models.Current
import alidoran.third_party.apis.rest.models.Location
import alidoran.third_party.apis.rest.retrofit_center_run.WeatherApi
import alidoran.third_party.apis.rest.retrofit_center_run.WeatherService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.third_party.databinding.ActivityRetrofitBinding
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RetrofitActivity : AppCompatActivity() {

    lateinit var binding: ActivityRetrofitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCallBack.setOnClickListener {
            WeatherApi.instance.weatherNow("Tehran", object : WeatherService {
                override fun location(location: Location) {
                    val txt = "Location = $location.name || localTime = ${location.localtime}"
                    Toast.makeText(baseContext, txt, Toast.LENGTH_LONG).show()
                }

                override fun current(current: Current) {

                }
            })
        }

        binding.btnNoAuth.setOnClickListener {
            lifecycleScope.launch {
                val response = getWeatherServiceLiveData().getWeatherApi2(q = "Tehran")
                var raw = response.raw()
                val body = response.raw()
                val errorBody = response.errorBody()
                Log.d("btnNoAuth", "onCreate:  $body   $errorBody")
            }
        }

        binding.btnBasicAuth.setOnClickListener {
            lifecycleScope.launch {
                val response = getBasicAuth().getWeatherApi2(q = "Tehran")
                var raw = response.raw()
                val body = response.raw()
                val errorBody = response.errorBody()
                Log.d("btnNoAuth", "onCreate:  $body   $errorBody")
            }
        }
    }
}