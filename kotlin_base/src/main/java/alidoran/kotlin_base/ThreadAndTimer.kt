package ir.alidoran.teach_kotlin

import android.os.HandlerThread
import android.os.Looper
import androidx.annotation.UiThread
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.logging.Handler
import kotlin.concurrent.schedule

fun main() {
    threadExecutor()
}

fun threadExecutor(){
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

fun uiThread(){
    android.os.Handler(Looper.getMainLooper()).post {
        TimeUnit.SECONDS.sleep(2)
        //Your code
    }
}



