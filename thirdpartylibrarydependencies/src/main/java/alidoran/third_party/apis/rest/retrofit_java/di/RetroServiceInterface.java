package alidoran.third_party.apis.rest.retrofit_java.di;

import android.net.Uri;

import alidoran.third_party.apis.rest.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetroServiceInterface {

    @GET
    Call<WeatherModel> getDataFromApi(@Url Uri url);
}
