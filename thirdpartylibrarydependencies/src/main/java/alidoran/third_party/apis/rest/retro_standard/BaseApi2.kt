package alidoran.third_party.apis.rest.retro_standard

import alidoran.android.paging_view.BasicAuthInterceptor
import alidoran.third_party.rx_java.WeatherServiceRx
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_ADDRESS = "https://api.weatherapi.com/"

fun createNoAuthBuilder(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(BASE_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun getRetroBasicAuthInstance(): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("Ali", "Doan"))
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_ADDRESS)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getWeatherServiceLiveData(): WeatherServiceLivedata {
    return createNoAuthBuilder().create(WeatherServiceLivedata::class.java)
}

fun getWeatherServiceRx(): WeatherServiceRx {
    return createNoAuthBuilder().create(WeatherServiceRx::class.java)
}

fun getBasicAuth(): WeatherServiceLivedata{
    return getRetroBasicAuthInstance().create(WeatherServiceLivedata::class.java)
}
