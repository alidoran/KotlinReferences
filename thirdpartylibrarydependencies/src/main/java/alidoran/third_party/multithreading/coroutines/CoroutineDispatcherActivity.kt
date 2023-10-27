package alidoran.third_party.multithreading.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.third_party.databinding.ActivityCoroutineDispatcherBinding
import androidx.activity.viewModels

class CoroutineDispatcherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineDispatcherBinding
    private val vm : CoroutineDispatcherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCoroutineDispatcherBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnIoLife.setOnClickListener { vm.testDispatcherLifeCycle() }
    }


}