package alidoran.third_party.di.constructor

import alidoran.third_party.databinding.ActivityDaggerBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ConstructorInjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDaggerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAcDagger.setOnClickListener{
            DaggerAirConditionDagFactory.create().inject().start()
        }
    }
}