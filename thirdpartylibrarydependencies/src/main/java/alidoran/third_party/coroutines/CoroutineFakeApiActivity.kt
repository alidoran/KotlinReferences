package alidoran.third_party.coroutines

import alidoran.third_party.databinding.ActivityCoroutineFakeApiBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.concurrent.TimeUnit

class CoroutineFakeApiActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineFakeApiBinding
    private val result = "Result"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineFakeApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCoroutineScope.setOnClickListener{
            //Io: network or database request
            //Main: work on the main thread
            //Default: Heavy computation
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }

    private suspend fun accessViewFromThread(inputText:String){
        withContext(Main){
            binding.txtCoroutineScope.text = inputText
        }
    }

    private suspend fun fakeApiRequest() {
        val result = getResultFromApi()
        println("result:$result")
        accessViewFromThread(result)
    }

    private suspend fun getResultFromApi(): String {
        logThread("getResultFromApi")
        delay(TimeUnit.SECONDS.toMillis(3))
        return result
    }

    private fun logThread(methodName: String) {
        println("debug : $methodName: ${Thread.currentThread().name}")
    }
}