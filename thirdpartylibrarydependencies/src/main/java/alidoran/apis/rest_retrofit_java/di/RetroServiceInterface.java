package alidoran.apis.rest_retrofit_java.di;

import android.net.Uri;

import com.example.retrofitteach.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetroServiceInterface {

    @GET
    Call<WeatherModel> getDataFromApi(@Url Uri url);
}
