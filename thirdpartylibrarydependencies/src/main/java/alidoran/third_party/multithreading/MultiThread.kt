package alidoran.third_party.multithreading

import alidoran.third_party.databinding.ActivityMultiThreadBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MultiThread : AppCompatActivity() {

    private lateinit var binding: ActivityMultiThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHelloWorld.setOnClickListener {
            startActivity(Intent(this, HelloWorldMultiThread::class.java))
        }

        binding.btnCounter.setOnClickListener {
            startActivity(Intent(this, CounterMultiThread::class.java))
        }
    }
}