package alidoran.kotlin_base

import android.os.Looper
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

fun main() {
    threadExecutor()
}

fun threadExecutor() {
    Thread().run {
        println("Hello Ali")
    }
}

fun threadWaiter() {
    Thread.sleep(TimeUnit.SECONDS.toMillis(5))
    println("Hello Ali")
}

fun timerExecutor() {
    Timer().schedule(TimeUnit.SECONDS.toMillis(5)) {
        println("Hello Ali")
    }
}

fun executorDelay() {
    Executors.newSingleThreadScheduledExecutor().schedule({
        println("Hello Ali")
    }, 5, TimeUnit.SECONDS)
}

fun uiThread() {
    android.os.Handler(Looper.getMainLooper()).post {
        TimeUnit.SECONDS.sleep(2)
        //Your code
    }
}

object VolatileZone {
    @Volatile  //This annotation help to the this field to be visible for other threads
    private var volatileObject = true
}





