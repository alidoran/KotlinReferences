package alidoran.third_party.di.method_injection

import alidoran.third_party.databinding.ActivityMethodInjectionBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MethodInjectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMethodInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carMethodInjection = DaggerCarMethodInjectionComponent.create().inject()
        carMethodInjection.start()
    }
}