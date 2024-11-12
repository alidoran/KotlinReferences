package alidoran.android.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityInternetBinding
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class InternetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInternetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            telephonyManager()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun telephonyManager(){
        lifecycleScope.launch {
            Detector5GViewModel(application).telephonyType.collect {
                binding.txtNetState.text = it
            }
        }
    }
}