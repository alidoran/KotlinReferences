package alidoran.android.mvvm_livedata.view

import alidoran.android.databinding.ActivityMvvmMainBinding
import alidoran.android.mvvm_livedata.view_model.MvvmViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MvvmMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmMainBinding
    private val viewModel: MvvmViewModel by viewModels() //add <implementation 'androidx.fragment:fragment-ktx:1.4.1'> to gradle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        viewModel.mvvmData.observe(this){
            mvvmModel -> binding.model = mvvmModel
        }
    }

}