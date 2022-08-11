package alidoran.android.activity_result_launcher

import alidoran.android.databinding.ActivityResultLauncherDestBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityResultLauncherDest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultLauncherDestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent().putExtra("ali", "AliDoran")
            setResult(RESULT_OK,intent)
            finish()
        }


    }
}