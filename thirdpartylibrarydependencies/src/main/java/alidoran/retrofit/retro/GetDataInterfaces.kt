package alidoran.retrofit.retro

import android.net.Uri
import com.example.retrofitteach.models.WeatherModel
import retrofit2.Call
import retrofit2.http.*



interface GetData {
    interface WeatherInterface {
        @GET
        fun presentWeather(@Url url:Uri): Call<WeatherModel>
    }
}