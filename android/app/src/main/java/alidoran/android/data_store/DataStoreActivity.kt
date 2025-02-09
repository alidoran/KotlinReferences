package alidoran.android.data_store

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import alidoran.android.R
import alidoran.android.databinding.ActivityDataStoreBinding
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        binding.LoadSampleText.setOnClickListener {
            lifecycleScope.launch {
                preferencesHelper.getSampleText().collect { sampleText ->
                    Log.d("DataStore", "Sample Boolean: $sampleText")
                    binding.TextView.text = sampleText
                }
            }
        }

        binding.SaveSampleText.setOnClickListener{
            binding.SaveSampleText.setOnClickListener {
                val text = binding.InputText.text.toString()
                lifecycleScope.launch {
                    preferencesHelper.saveSampleText(text)
                }
            }
        }
    }
}