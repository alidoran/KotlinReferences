package alidoran.third_party.apis.rest.retro_dagger


import alidoran.third_party.apis.rest.models.WeatherModel
import retrofit2.Response
import retrofit2.http.*
    interface WeatherService3 {
        @GET("v1/current.json")
        suspend fun getWeatherApi3(
            @Query("key")
            key: String = "339a563b08894e889e8125922220706",
            @Query("q")
            q: String,
            @Query("aqi")
            aqi: String = "no"
        ): Response<WeatherModel>
    }