package alidoran.third_party.multithreading.coroutines

import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

object CoroutinesContextHelper {
    @OptIn(DelicateCoroutinesApi::class)
    fun CoroutineScope.readContextName(jobs: ArrayList<Job>) {
        jobs += launch {
            println("Without dispatcher: ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.Default) {
            println("default: ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.Unconfined) {
            println("Unconfined: ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.IO) {
            println("IO: ${Thread.currentThread().name}")
        }
        jobs += launch(newSingleThreadContext("mThread")) {
            println("mThread: ${Thread.currentThread().name}")
        }
    }

    fun unconfined() = runBlocking {
        launch(Dispatchers.Unconfined) {
            println("BeforeDelay: ${Thread.currentThread().name}")
            delay(1000)
            println("AfterDelay: ${Thread.currentThread().name}")
        }
    }

    fun accessJobInItself() = runBlocking {
        val job = launch {
            println("isActive = ${coroutineContext[Job]!!.isActive}")
        }
        job.join()
    }

    suspend fun coroutineScopeListenerSample(seconds: Long, unit: () -> Unit)
    {
        coroutineScope {
            launch {
                delay(TimeUnit.SECONDS.toMillis(seconds))
                unit.invoke()
            }
            println("Hello")
        }
    }
}

