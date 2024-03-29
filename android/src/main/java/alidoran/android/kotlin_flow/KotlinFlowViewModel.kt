package alidoran.android.kotlin_flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeCallOneToThreeApi
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeIntRepeatRequest
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeStringLongDelayRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Suppress("unused", "UNUSED_VARIABLE")
@Singleton
class KotlinFlowViewModel : ViewModel() {

    fun launchFlow(methodName: String, flow: Flow<*>) {
        viewModelScope.launch {
            flow.collect { Log.d(methodName, it.toString()) }
        }
    }

    fun simpleLaunchFlow() {
        val intFlow = fakeCallOneToThreeApi()
        launchFlow("simpleLaunchFlow", intFlow)
    }

    fun flowMap() {
        val intFlow = fakeCallOneToThreeApi()
            .map { it.toString() }
        launchFlow("flowMap", intFlow)
    }

    fun flowFilter() {
        val intFlow = fakeCallOneToThreeApi()
            .map { it.toString() }
            .filter { it.endsWith("0") }
        launchFlow("flowFilter", intFlow)
    }

    fun flowCatch() {
        val intFlow = fakeCallOneToThreeApi()
            .map { it.toString() }
            .catch {
                Log.d("flowCatch", "flowCatchError")
                emit("")
            }
        launchFlow("flowCatch", intFlow)
    }

    suspend fun transform(): Flow<String> {
        return fakeIntRepeatRequest(3).transform<Int, String> {
            emit(it.toString())
        }
    }

    suspend fun transformWhile() {
        fakeIntRepeatRequest(3).transformWhile<Int, String> {
            //Do something and return true
            val a = it + 1 //save a on BG
            true
        }
    }

    suspend fun multiEmit(){
        fakeIntRepeatRequest(3).collect{

        }
    }

    fun mapLearn(): Flow<FlowModelName> {
        val model = FlowModelName("Ali Doran")
        return flow { emit(model) }
    }

    fun longDelay(){
        CoroutineScope(IO).launch {
            fakeStringLongDelayRequest().collect{
                Log.d("longDelay", "response received $it")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}