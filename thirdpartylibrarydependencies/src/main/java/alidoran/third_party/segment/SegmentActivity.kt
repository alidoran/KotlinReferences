package alidoran.third_party.segment

import alidoran.third_party.databinding.ActivitySegmentBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SegmentActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySegmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Create an analytics client with the given application context and Segment write key.
// NOTE: in android, application context is required to pass as the second parameter.
        Analytics("61GdrLMTEnsCqskKTDV8ah4iElx7IsEm", applicationContext) {
            // Automatically track Lifecycle events
            trackApplicationLifecycleEvents = true
            flushAt = 3
            flushInterval = 10
        }


    }
}