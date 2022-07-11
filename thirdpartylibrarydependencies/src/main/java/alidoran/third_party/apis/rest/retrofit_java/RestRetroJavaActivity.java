package alidoran.third_party.apis.rest.retrofit_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import alidoran.third_party.databinding.ActivityRestRetroJavaBinding;

public class RestRetroJavaActivity extends AppCompatActivity {

    private ActivityRestRetroJavaBinding binding;
    private RetroJavaViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestRetroJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData(){
        viewModel = new ViewModelProvider(this).get(RetroJavaViewModel.class);
        viewModel.getRecycler().observe(this, weatherModel -> {
            if (weatherModel !=null){
                System.out.println(weatherModel.getLocation());
            }else{

            }
        });
        viewModel.makeApiCall();
    }
}