package alidoran.third_party.apis.rest.retrofit_center_run

import android.net.Uri
import alidoran.third_party.apis.rest.models.WeatherModel
import retrofit2.Call
import retrofit2.http.*

interface GetData {
    interface WeatherInterface {
        @GET
        fun presentWeather(@Url url:Uri): Call<WeatherModel>
    }
}