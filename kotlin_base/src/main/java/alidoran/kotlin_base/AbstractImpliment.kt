package ir.alidoran.teach_kotlin

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

fun main() {
    ApiWaiterSimulator(object : ApiWaiterSimulatorAbstract() {

        override fun before(){
            super.before()
            println("Progress Start main")
        }

        override fun finish() {
            println("OnFinishCallRun")
        }
    }).start()
}

class ApiWaiterSimulator(var apiWaiterSimulatorInterface: ApiWaiterSimulatorAbstract) {
    fun start() {
        apiWaiterSimulatorInterface.before()
        val executorService: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        executorService.schedule({
            apiWaiterSimulatorInterface.finish()
        }, 5, TimeUnit.SECONDS)
    }
}

abstract class ApiWaiterSimulatorAbstract {
    open fun before(){println("Progress Start abstract")}
    abstract fun finish()
}



