package alidoran.third_party.multithreading

import alidoran.third_party.databinding.ActivityMultiThreadingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

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
        }
    }

    private fun startNum() = binding.txtStart.text.toString().toInt() * 1_000_000

    private fun endNum() = binding.txtEnd.text.toString().toInt() * 1_000_000

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
}