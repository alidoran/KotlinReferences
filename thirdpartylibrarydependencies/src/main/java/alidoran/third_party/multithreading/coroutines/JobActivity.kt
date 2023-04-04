package alidoran.third_party.multithreading.coroutines

import alidoran.third_party.databinding.ActivityJobBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class JobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnSimpleJob.setOnClickListener {
            JobHelper.simpleJob()
        }

        btnCancelJob.setOnClickListener {
            JobHelper.cancelJob()
        }

        btnCancelBlockScopeIssue.setOnClickListener {
            JobHelper.cancelBlockScopeIssue()
        }

        btnCancelBlockScope.setOnClickListener {
            JobHelper.cancelBlockScope()
        }

        btnCancelExceptionMessage.setOnClickListener {
            JobHelper.cancelExceptionMessage()
        }

        btnCancelTimeOutException.setOnClickListener {
            JobHelper.cancelWithTimeOutException()
        }

        btnCancelTimeOutCustomException.setOnClickListener {
            JobHelper.cancelWithCustomTimeOutException()
        }
    }
}