package alidoran.third_party.rx_java

import alidoran.third_party.apis.rest.models.WeatherModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceRx {
        @GET("v1/current.json")
        fun getWeatherRx(
            @Query("key")
            key: String = "339a563b08894e889e8125922220706",
            @Query("q")
            q: String,
            @Query("aqi")
            aqi: String = "no"
        ): Observable<WeatherModel>
    }