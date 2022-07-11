package alidoran.third_party.apis.rest.retro_dagger

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroModule {
    private val baseUrl = "https://api.weatherapi.com/"

    @Singleton
    @Provides
    fun getWeatherApi(retrofit: Retrofit): WeatherService3 {
        return retrofit.create(WeatherService3::class.java)
    }

    @Singleton
    @Provides
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}