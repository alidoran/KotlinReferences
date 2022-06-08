package alidoran.di.constructor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityDaggerBinding

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