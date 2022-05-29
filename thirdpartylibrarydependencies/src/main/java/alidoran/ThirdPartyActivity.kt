package alidoran

import alidoran.coroutines.CoroutineActivity
import alidoran.di.DiActivity
import alidoran.di.simple_di.SimpleDiActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityThirdpartyBinding

class ThirdPartyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThirdpartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDi.setOnClickListener{
            val intent = Intent(this, DiActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoroutine.setOnClickListener{
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }
    }
}