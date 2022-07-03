package alidoran.apis.rest_retrofit_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.alidoran.thirdpartylibrarydependencies.R;
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityRestRetroJavaBinding;
import com.example.retrofitteach.models.WeatherModel;

public class RestRetroJavaActivity extends AppCompatActivity {

    private ActivityRestRetroJavaBinding binding;
    private RestRetroJavaViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestRetroJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData(){
        viewModel = new ViewModelProvider(this).get(RestRetroJavaViewModel.class);
        viewModel.getRecycler().observe(this, weatherModel -> {
            if (weatherModel !=null){
                System.out.println(weatherModel.getLocation());
            }else{

            }
        });
        viewModel.makeApiCall();
    }
}