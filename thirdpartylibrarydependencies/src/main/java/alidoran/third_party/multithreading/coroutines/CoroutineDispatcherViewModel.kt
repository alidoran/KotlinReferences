package alidoran.third_party.multithreading.coroutines

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CoroutineDispatcherViewModel: ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun testDispatcherLifeCycle(){
        logScopeStatus()
        coroutineScope.launch {
            FakeEndpoint.fakeStringLongDelayRequest()
                .collect {
                    Log.d("testDispatcherLifeCycle", "testDispatcherLifeCycle: $it")
                    coroutineScope.cancel()
                }
        }
    }

    private fun logScopeStatus(){
        CoroutineScope(Main).launch {
            delay(TimeUnit.SECONDS.toMillis(4))
            while(true) {
                val state = coroutineScope.isActive
                Log.d("logScopeStatus", "logScopeStatus: $state")
                delay(TimeUnit.SECONDS.toMillis(1))
            }
        }
    }
}