package project.mvvm_livedata.view

import alidoran.android_fundamental.databinding.ActivityMvvmMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import project.mvvm_livedata.model.MvvmModel
import project.mvvm_livedata.view_model.MvvmViewModel

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