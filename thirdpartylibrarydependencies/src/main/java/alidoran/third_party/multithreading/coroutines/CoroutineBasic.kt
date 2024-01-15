package alidoran.third_party.multithreading.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

@OptIn(DelicateCoroutinesApi::class)
@Suppress("unused")
class CoroutinesBasic {
    private fun firstSimpleCoroutine() =
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                delay(TimeUnit.SECONDS.toMillis(3)) // non-blocking delay for 1 second (default time unit is ms)
                println("World!") // print after delay

            }
            println("Hello") // main coroutine continues while a previous one is delayed
        }


    //region suspend
    fun suspendCoroutine() {
        runBlocking { // this: CoroutineScope
            launch {
                printWorld()
            }
            println("Hello")
        }
    }

    suspend fun printWorld() {
        delay(TimeUnit.SECONDS.toMillis(3))
        println("World")
    }
//endregion

    //region coroutineScope
    suspend fun runCoroutineScope() =
        runBlocking {
            coroutineScopeSample()
        }


    private suspend fun coroutineScopeSample() =
        coroutineScope {
            launch {
                delay(TimeUnit.SECONDS.toMillis(3))
                println("World!")
            }
            println("Hello")
        }
//endregion

    //region coroutines start
    fun withoutCoroutinesStart() {
        val job = GlobalScope.launch {
            delay(200)
            println("jobScope")
            delay(200)
        }

        runCoroutineStart(job)
    }

    fun withCoroutinesStart() {
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            delay(200)
            println("jobScope")
            delay(200)
        }

        runCoroutineStart(job)
    }

    fun runCoroutineStart(job: Job){
        GlobalScope.launch {
            delay(200)
            println("Coroutines Scope1")
            job.start()
            println("Coroutines Scope2")
            delay(200)
        }
        Thread.sleep(1000)
    }
    //endregion
}