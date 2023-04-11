package alidoran.third_party.multithreading.coroutines

import kotlinx.coroutines.*

object JobHelper {
    fun simpleJob() = runBlocking {
        val job = launch {
            delay(1000)
            print("World")
        }
        print("Hello, ")
        job.join()
        println("!")
    }

    fun cancelJob() = runBlocking {
        val job = launch {
            while (true) {
                delay(100)
                print(".")
            }
        }
        delay(2500)
        job.cancelAndJoin()
        println("Done")
    }

    fun cancelBlockScopeIssue() = runBlocking {
        //job can't cancel till block-scope finish
        val job = launch {
            repeat(10000) {
                print(".")
                Thread.sleep(1)
            }
        }
        delay(1000)
        job.cancelAndJoin()
        println("Done")
    }

    fun cancelBlockScope() = runBlocking {
        val job = CoroutineScope(Dispatchers.Default).launch {
            repeat(10000) {
//                if (!isActive) throw CancellationException()
                if (!isActive) return@launch
                print(".")
                Thread.sleep(10)
            }
        }
        delay(1500)
        job.cancelAndJoin()
        println("Done")
    }

    fun cancelExceptionMessage() = runBlocking {
        val job = launch {
            try {
                while (true) {
                    yield()
                    print(".")
                    Thread.sleep(10)
                }
            } catch (ex: CancellationException) {
                println()
                println("Cancelled: ${ex.message}")
            } finally {
                println()
                println("Finished")
            }
        }
        delay(100)
        job.cancel("Too many jobs")
        job.join()
    }

    fun cancelWithTimeOutException() = runBlocking {
        withTimeout(1000) {
            while (true) {
                print(".")
                delay(10)
            }
        }
    }

    fun cancelWithCustomTimeOutException() = runBlocking {
        val job = withTimeoutOrNull(1000) {
            while (true) {
                print(".")
                delay(10)
            }
        }
        if (job == null) println("timeOut")
    }
}