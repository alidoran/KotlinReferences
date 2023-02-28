package alidoran.third_party.multithreading

import alidoran.third_party.R
import alidoran.third_party.databinding.ActivityHelloWorldMultiThreadBinding
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class HelloWorldMultiThread : AppCompatActivity() {

    private lateinit var binding: ActivityHelloWorldMultiThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloWorldMultiThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSleep.setOnClickListener { helloWorldSleep() }
        binding.btnCoroutineLaunch.setOnClickListener { helloWorldDelay() }

    }

    @SuppressLint("SetTextI18n")
    private fun helloWorldSleep() = with(binding.txtSleep) {
        //Sleep blocked the thread
        text = ""
        thread {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3))
            runOnUiThread { text = "$text world" }
        }
        text = "$text Hello"
    }

    @SuppressLint("SetTextI18n")
    private fun helloWorldDelay() = with(binding.txtCoroutineLaunch) {
        // Opposite of sleep, delay not blocked the thread
        text = "Launch/Delay: "
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3)) //Main different in delay is not-block thread
            runOnUiThread { text = "$text world" }
        }
        text = "$text Hello"
    }
}