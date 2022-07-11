package alidoran.android.mvvm_livedata.view

import alidoran.android.databinding.ActivityMvvmMainBinding
import alidoran.android.mvvm_livedata.model.MvvmModel
import alidoran.android.mvvm_livedata.view_model.MvvmViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MvvmMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmMainBinding
    private val model: MvvmViewModel by viewModels() //add <implementation 'androidx.fragment:fragment-ktx:1.4.1'> to gradle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        model.getLiveMvvmData().observe(this) { mvvmModel: MvvmModel ->
            binding.model = mvvmModel
        }
    }
}