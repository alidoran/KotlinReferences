package alidoran.third_party.apis.rest.retro_standard

import alidoran.third_party.rx_java.WeatherServiceRx
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_ADDRESS = "https://api.weatherapi.com/"

private fun createBuilder(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(BASE_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun getWeatherServiceLiveData(): WeatherServiceLivedata {
    return createBuilder().create(WeatherServiceLivedata::class.java)
}

fun getWeatherServiceRx(): WeatherServiceRx {
    return createBuilder().create(WeatherServiceRx::class.java)
}
