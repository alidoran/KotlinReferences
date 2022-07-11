package alidoran.third_party.di.simple_di

import alidoran.third_party.databinding.ActivitySimpleDiBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class SimpleDiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySimpleDiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWithDi.setOnClickListener{
            val engine = EngineSimple()
            WithDISimple(engine).drive()
        }

        binding.btnWithoutDi.setOnClickListener{
            WithoutDISimple().drive()
        }

        binding.btnAirConditionSample.setOnClickListener{
            val airCondition = AirConditionFactory().get()
            airCondition.start()
        }
    }
}