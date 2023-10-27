package alidoran.android.worker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityWorkerBinding
import android.util.Log

class WorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnLongProcessWithoutWorker.setOnClickListener { com.example.commonlibrary.fake_endpoint.FakeEndpoint.longProcess() }
        btnLongProcessWithWorker.setOnClickListener {  }
    }

    private fun longProcessWithoutWorker(){
        val a = com.example.commonlibrary.fake_endpoint.FakeEndpoint.longProcess()
        Log.d("TAG", "longProcessWithoutWorker: $a")
    }
}