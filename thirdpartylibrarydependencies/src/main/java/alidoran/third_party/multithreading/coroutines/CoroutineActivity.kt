package alidoran.third_party.multithreading.coroutines

import alidoran.third_party.databinding.ActivityCoroutineBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CoroutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFakeApi.setOnClickListener{
            val intent = Intent(this, CoroutineFakeApiActivity::class.java)
            startActivity(intent)
        }

        binding.btnProgressbar.setOnClickListener{
            val intent = Intent(this, CoroutineProgressBarActivity::class.java)
            startActivity(intent)
        }
    }
}