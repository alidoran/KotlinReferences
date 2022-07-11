package alidoran.third_party.coroutines

import alidoran.third_party.databinding.ActivityCoroutineProgressBarBinding
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.concurrent.TimeUnit

class CoroutineProgressBarActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineProgressBarBinding
    private val PROGRESS_MAX = 100
    private val PROGRESS_MIN = 0
    private val JOB_TIME = TimeUnit.SECONDS.toMillis(4)
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStopStart.setOnClickListener {
            if (!::job.isInitialized) {
                initJob()
            }
            binding.progressbar.startJobOrCancel(job)
        }
    }

    private fun initJob() {
        val txt = "Start job #1"
        binding.btnStopStart.text = txt
        binding.txtResult.text = ""
        job = Job()
        job.invokeOnCompletion {
            val msg: String = it?.message ?: "Unknown cancellation error"
            println("$job was cancelled. reason is $msg")
//            Toast.makeText(this@CoroutineProgressBar, msg, Toast.LENGTH_LONG).show()
        }
        binding.progressbar.min = PROGRESS_MIN
        binding.progressbar.max = PROGRESS_MAX
    }

    private fun ProgressBar.startJobOrCancel(job: Job) {
        if (this.progress > 0) {
            resetJob()
        } else {
            val txt = "Cancel Job #1"
            binding.btnStopStart.text = txt
            CoroutineScope(IO + job).launch {
                for (i in PROGRESS_MIN..PROGRESS_MAX) {
                    delay((JOB_TIME / PROGRESS_MAX))
                    this@startJobOrCancel.progress = i
                }
                updateJobCompleteTextView("Job is complete")
            }
        }
    }

    private suspend fun updateJobCompleteTextView(text: String) =
        withContext(Main) {
            binding.txtResult.text = text
        }

    private fun resetJob() {
        if (job.isActive || job.isCompleted) {
            job.cancel(CancellationException("Resetting job"))
        }
        initJob()
    }


    private suspend fun toastMessage(text: String) =
        withContext(Main) {
            Toast.makeText(this@CoroutineProgressBarActivity, text, Toast.LENGTH_LONG).show()
        }
}
