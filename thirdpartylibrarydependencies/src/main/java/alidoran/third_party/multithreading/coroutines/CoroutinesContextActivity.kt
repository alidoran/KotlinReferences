package alidoran.third_party.multithreading.coroutines

import alidoran.third_party.databinding.ActivityCoroutinesContextBinding
import alidoran.third_party.multithreading.coroutines.CoroutinesContextHelper.readContextName
import alidoran.third_party.multithreading.coroutines.CoroutinesContextHelper.accessJobInItself
import alidoran.third_party.multithreading.coroutines.CoroutinesContextHelper.unconfined
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class CoroutinesContextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesContextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesContextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnThreadName.setOnClickListener { readThreadName() }
        btnUnconfinedDispatcher.setOnClickListener { unconfined() }
        btnAccessJobInItself.setOnClickListener { accessJobInItself() }
    }

    private fun readThreadName() = runBlocking {
        val jobs = arrayListOf<Job>()
        readContextName(jobs)
        jobs.forEach { it.join() }
    }


}