package alidoran.apis.rest_retrofit_java;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitteach.models.WeatherModel;

import java.util.List;

import javax.inject.Inject;

import alidoran.apis.rest_retrofit_java.di.RetroModule;
import alidoran.apis.rest_retrofit_java.di.RetroServiceInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestRetroJavaViewModel extends AndroidViewModel {

    @Inject
    RetroServiceInterface mService;

    private MutableLiveData<WeatherModel> liveData;

    public RestRetroJavaViewModel(@NonNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        DaggerRetroComponent.builder().retroModule(new RetroModule()).build().inject(this);
    }

    public MutableLiveData<WeatherModel> getRecycler() {
        return liveData;
    }

    public void makeApiCall() {
        Call<WeatherModel> call = mService.getDataFromApi(Uri.parse("v1/current.json?key="+ "339a563b08894e889e8125922220706" +"&q=$cityName&aqi=no"));
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful())
                    liveData.postValue(response.body());
                else
                    liveData.postValue(null);
        }

        @Override
        public void onFailure (Call <WeatherModel> call, Throwable t){
                liveData.postValue(null);
        }
    });
}
}
