package alidoran.third_party.multithreading

import alidoran.third_party.databinding.ActivityMultiThreadingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class MultiThreading : AppCompatActivity() {

    private lateinit var binding: ActivityMultiThreadingBinding
    private val seq = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMultiThreadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val arrayList = createArray()
            singleThread(arrayList)
            coroutineThread(arrayList)
            helloWorldSleep()
            helloWorldDelay()
            helloWorldMultipleThread()
        }
    }

    private fun startNum() = binding.txtStart.text.toString().toInt() * 1_000

    private fun createArray(): IntArray {
        val result = mutableListOf<Int>()
        var limit = startNum()
        while (limit > 0) {
            result.add((0..50000).random())
            limit--
        }
        return result.toIntArray()
    }

    private fun singleThread(array: IntArray) {
        val printResult = StringBuilder().append("The SingleThread result is ")
        var result: Long
        val time = measureTimeMillis {
            result = computeSingle(array, 0, array.size)
        }
        printResult.append("$result and calculate time is $time")
        binding.txtSingleThread.text = printResult
    }

    private fun coroutineThread(array: IntArray) {
        val printResult = StringBuilder().append("The coroutineThread result is ")
        var result: Long
        val time = measureTimeMillis {
        runBlocking {
                result = computeCoroutine(array, 0, array.size)
            }
        }
        printResult.append("$result and calculate time is $time")
        binding.txtAsyncThread.text = printResult
    }

    private fun computeSingle(array: IntArray, low: Int, high: Int): Long {
        return if (high - low <= seq) {
            (low until high)
                .sumOf { array[it].toLong() }
        } else {
            val mid = low + (high - low) / 2
            val left = computeSingle(array, low, mid)
            val right = computeSingle(array, mid, high)
            return left + right
        }
    }

    private suspend fun computeCoroutine(array: IntArray, low: Int, high: Int): Long {
        return if (high - low <= seq) {
            (low until high)
                .sumOf { array[it].toLong() }
        } else {
            val mid = low + (high - low) / 2
            val left = CoroutineScope(IO).async { computeCoroutine(array, low, mid) }
            val right = computeCoroutine(array, mid, high)
            return left.await() + right
        }
    }

    private fun helloWorldSleep() = with(binding.txtSleep){
        //Sleep blocked the thread
        text ="Thread/sleep: "
        thread {
            sleep(TimeUnit.SECONDS.toMillis(3))
            text = "$text world"
        }
        text = "$text Hello"
    }

    private fun helloWorldDelay()= with(binding.txtDelay){
        // Opposite of sleep, delay not blocked the thread
        text ="Launch/Delay: "
        CoroutineScope(IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            text = "$text world"
        }
        text = "$text Hello"
    }

    private fun helloWorldMultipleThread()= with(binding.txtMultipleThread){
        val result = AtomicInteger()
        for (i in 1..1_500_500){
            thread(start = true) {
                result.getAndIncrement()
            }
        }
        sleep(1000) //just for waiting for all result
        print(result)
    }
}