package alidoran.third_party.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    suspendCoroutine()
}
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