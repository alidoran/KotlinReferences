package alidoran.third_party.apis.rest.retrofit_center_run

import android.net.Uri
import alidoran.third_party.apis.rest.models.WeatherModel
import retrofit2.Call


class WeatherApi private constructor(){
    private val basAddress = "https://api.weatherapi.com/"
    private val weatherKey = "339a563b08894e889e8125922220706"
    private val baseApi= BaseApi.instance

    companion object {
        var instance = WeatherApi()
    }

    fun weatherNow(cityName: String,weatherService: WeatherService){
        val uri = Uri.parse("v1/current.json?key=$weatherKey&q=$cityName&aqi=no")
        val directApi: GetData.WeatherInterface = baseApi.createBuilder(basAddress)!!.create(GetData.WeatherInterface::class.java)
        val weatherCall: Call<WeatherModel> = directApi.presentWeather(uri)
        baseApi.enqueue(weatherCall, object: ApiEnqueueListener() {
            override fun <T> onSuccess(result: T) {
                weatherService.location((result as WeatherModel).location)
                weatherService.current((result as WeatherModel).current)
            }
        })
    }
}