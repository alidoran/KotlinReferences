package alidoran.third_party.multithreading.coroutines

import alidoran.third_party.databinding.ActivityRunBlockingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunBlockingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRunBlockingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRunBlockingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnRunBlock.setOnClickListener {
            runBlockingLearn()
        }

        btnRunBlockEntire.setOnClickListener {
            runBlockingEntireFun()
        }
    }

    private fun runBlockingLearn() {
        lifecycleScope.launch {
            delay(1000)
            println("World")
        }

        print("Hello, ")
        runBlocking {
            delay(1500)
            println("!")
        }
    }

    private fun runBlockingEntireFun() = runBlocking {
        lifecycleScope.launch {
            delay(1000)
            print("World")
        }

        print("Hello, ")
        delay(1500)
        println("!")
    }
}