package alidoran.di

import alidoran.di.retrofit.DaggerActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityDiBinding
import com.alidoran.thirdpartylibrarydependencies.di.retrofit.AirConditionFactory


class DiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDagger.setOnClickListener{
            val intent = Intent(this, DaggerActivity::class.java)
            startActivity(intent)
        }

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