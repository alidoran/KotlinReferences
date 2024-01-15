package alidoran.third_party.multithreading.coroutines

import alidoran.third_party.databinding.ActivityCoroutineBinding
import alidoran.third_party.multithreading.coroutines.eventbus.EventBusActivity
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

        binding.btnRunBlocking.setOnClickListener {
            val intent = Intent(this, RunBlockingActivity::class.java)
            startActivity(intent)
        }

        binding.btnJob.setOnClickListener {
            val intent = Intent(this, JobActivity::class.java)
            startActivity(intent)
        }

        binding.btnJob.setOnClickListener {
            val intent = Intent(this, JobActivity::class.java)
            startActivity(intent)
        }

        binding.btnChannel.setOnClickListener {
            val intent = Intent(this, CoroutinesChannelActivity::class.java)
            startActivity(intent)
        }

        binding.btnDispatchers.setOnClickListener {
            val intent = Intent(this, CoroutineDispatcherActivity::class.java)
            startActivity(intent)
        }

        binding.btnEventBus.setOnClickListener {
            val intent = Intent(this, EventBusActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoroutineStart.setOnClickListener{
            CoroutinesBasic().withCoroutinesStart()
        }

        binding.btnWithoutCoroutineStart.setOnClickListener{
            CoroutinesBasic().withoutCoroutinesStart()
        }
    }
}