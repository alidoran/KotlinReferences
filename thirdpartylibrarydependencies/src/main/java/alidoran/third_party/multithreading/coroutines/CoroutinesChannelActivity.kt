package alidoran.third_party.multithreading.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.third_party.databinding.ActivityCoroutinesChannelBinding
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CoroutinesChannelActivity : AppCompatActivity() {

    private val channel = Channel<Int>()
    private lateinit var binding: ActivityCoroutinesChannelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesChannelBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSendReceive.setOnClickListener {
            receive()
            lifecycleScope.launch {
                for (x in 0..10) {
                    delay(2000)
                    channel.send(x)
                }
            }
        }

        binding.btnSendAsFlow.setOnClickListener {
            asFlow()
            lifecycleScope.launch {
                for (x in 0..10) {
                    delay(2000)
                    channel.send(x)
                }
            }
        }


    }

    private fun receive() {
        lifecycleScope.launch {
            repeat(10) {
                val number = channel.receive().toString()
                runOnUiThread {
                    Toast.makeText(
                        this@CoroutinesChannelActivity,
                        number,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun asFlow() {
        lifecycleScope.launch {
            channel.consumeAsFlow().collect {
                runOnUiThread {
                    Toast.makeText(
                        this@CoroutinesChannelActivity,
                        it.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}