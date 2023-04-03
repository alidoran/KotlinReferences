package alidoran.third_party.multithreading

import alidoran.third_party.databinding.ActivityCounterMultiThreadBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class CounterMultiThread : AppCompatActivity() {

    private lateinit var binding: ActivityCounterMultiThreadBinding
    private val seq = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCounterMultiThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrayList = createArray()

        binding.btnChangeInput.setOnClickListener { arrayList = createArray() }
        binding.btnSingleThread.setOnClickListener { singleThread(arrayList) }
        binding.btnAsyncCoroutine.setOnClickListener { coroutineThread(arrayList) }
        binding.btnMultipleThread.setOnClickListener { multipleThread() }
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
        val printResult = StringBuilder()
        var result: Long
        val time = measureTimeMillis {
            result = computeSingle(array, 0, array.size)
        }
        printResult.append("result:$result time:$time")
        binding.txtSingleThread.text = printResult
    }

    private fun coroutineThread(array: IntArray) {
        val printResult = StringBuilder()
        var result: Long
        val time = measureTimeMillis {
        runBlocking {
                result = computeCoroutine(array, 0, array.size)
            }
        }
        printResult.append("result:$result time:$time")
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

    private fun multipleThread()= with(binding.txtMultipleThread){
        //million thread will create
        val result = AtomicInteger()
        for (i in 1..1_500_000){
            thread(start = true) {
                result.getAndIncrement()
            }
        }
        sleep(1000) //just for waiting for all result
        print(result)
    }
}