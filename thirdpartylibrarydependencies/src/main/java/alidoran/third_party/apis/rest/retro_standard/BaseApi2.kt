package alidoran.third_party.apis.rest.retro_standard

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

fun getWeatherService2(): WeatherService2 {
    return createBuilder().create(WeatherService2::class.java)
}
