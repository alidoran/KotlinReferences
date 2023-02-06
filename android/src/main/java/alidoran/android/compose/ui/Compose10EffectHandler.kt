package alidoran.android.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class Compose10EffectHandler : ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent{

        }
            ApiWaiterSimulator(object : ApiWaiterSimulatorAbstract() {
                // Implementing this method is optional
                override fun before(){
                    // Calling abstract method first
                    super.before()
                    println("Progress Start main")
                }

                // Implementing this method is force
                override fun finish() {
                    println("OnFinishCallRun")
                }
            }).start()
    }

    @Composable
    fun clickSample(){
        Button(onClick = {

        }) {

        }
    }

}

class ApiWaiterSimulator(var apiWaiterSimulatorInterface: ApiWaiterSimulatorAbstract) {
    fun start() {
        apiWaiterSimulatorInterface.before()
        val executorService: ScheduledExecutorService =
            Executors.newSingleThreadScheduledExecutor()
        executorService.schedule({
            apiWaiterSimulatorInterface.finish()
        }, 5, TimeUnit.SECONDS)
    }
}

abstract class ApiWaiterSimulatorAbstract {
    open fun before() {
        println("Progress Start abstract")
    }

    abstract fun finish()
}
