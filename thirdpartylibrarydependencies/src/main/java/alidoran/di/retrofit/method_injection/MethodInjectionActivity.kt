package alidoran.di.retrofit.method_injection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityMethodInjectionBinding

class MethodInjectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMethodInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carMethodInjection = DaggerCarMethodInjectionComponent.create().inject()
        carMethodInjection.start()
    }
}