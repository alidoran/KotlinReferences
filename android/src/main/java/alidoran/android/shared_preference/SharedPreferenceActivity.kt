package alidoran.android.shared_preference

import alidoran.android.databinding.ActivitySharedPreferenceBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("mPreferenceName", MODE_PRIVATE)

        binding.btnPush.setOnClickListener {
            val inputText = binding.edtInput.text.toString()
            sharedPreferences.edit().apply {
                putString("mPreferenceFieldName", inputText).apply()
            }
        }

        binding.btnPull.setOnClickListener{
            binding.txtShow.text =
                sharedPreferences.getString("mPreferenceFieldName" , "")
        }
    }
}
