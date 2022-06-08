package alidoran.di.simple_di

import alidoran.di.constructor.ConstructorInjectActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityDiBinding
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivitySimpleDiBinding


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