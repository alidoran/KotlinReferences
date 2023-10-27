package alidoran.third_party.multithreading.coroutines.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.third_party.databinding.ActivityEventBusBinding
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class EventBusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBusBinding
    private val vm : EventBusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSimpleEventBus.setOnClickListener { simpleEvent() }
        binding.btnComplexEventBus.setOnClickListener { complexEvent() }
        binding.btnComplexEventGetValues.setOnClickListener { vm.getValuesCount() }
        binding.btnComplexEventResetValues.setOnClickListener{vm.clearValuesCount()}
        binding.btnComplexEventResetVmScope.setOnClickListener { vm.viewModelScope.cancel() }
    }

    private fun simpleEvent() {
        lifecycleScope.launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            EventBus.dispatch("Ali Doran")
            cancel()
        }
    }

    private fun complexEvent() {
        lifecycleScope.launch {
            repeat(200) {
                delay(100)
                EventBusComplex.post(('a'..'z').random())
            }
        }

        lifecycleScope.launch {
            repeat(200) {
                delay(80)
                EventBusComplex.post(Random.nextInt())
            }
        }

        lifecycleScope.launch {
            repeat(200) {
                delay(60)
                EventBusComplex.post(Random.nextBoolean())
            }
        }
    }

}