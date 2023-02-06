package alidoran.third_party.firebase.crashlytics

import alidoran.third_party.databinding.ActivityCrashlyticsBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CrashlyticsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCrashlyticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashlyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnManualCrash.setOnClickListener{
            throw RuntimeException("Test Crash")
        }

    }
}