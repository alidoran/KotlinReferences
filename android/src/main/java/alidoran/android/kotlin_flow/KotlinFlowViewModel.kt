package alidoran.android.kotlin_flow

import alidoran.android.fake_endpoint.FakeEndpoint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch

class KotlinFlowViewModel : ViewModel() {

    fun launchFlow(methodName: String, flow: Flow<*>) {
        viewModelScope.launch {
            flow.collect { Log.d(methodName, it.toString()) }
        }
    }

    fun simpleLaunchFlow() {
        val intFlow = FakeEndpoint.fakeCallOneToThreeApi()
        launchFlow("simpleLaunchFlow", intFlow)
    }

    fun flowMap() {
        val intFlow = FakeEndpoint.fakeCallOneToThreeApi()
            .map { it.toString() }
        launchFlow("flowMap", intFlow)
    }

    fun flowFilter() {
        val intFlow = FakeEndpoint.fakeCallOneToThreeApi()
            .map { it.toString() }
            .filter { it.endsWith("0") }
        launchFlow("flowFilter", intFlow)
    }

    fun flowCatch() {
        val intFlow = FakeEndpoint.fakeCallOneToThreeApi()
            .map { it.toString() }
            .catch {
                Log.d("flowCatch", "flowCatchError")
                emit("")
            }
        launchFlow("flowCatch", intFlow)
    }

    suspend fun transform(): Flow<String> {
        return FakeEndpoint.fakeIntReapeatRequest().transform<Int, String> {
            emit(it.toString())
        }
    }

    suspend fun transformWhile() {
        FakeEndpoint.fakeIntReapeatRequest().transformWhile<Int, String> {
            //Do something and return true
            val a = it + 1 //save a on BG
            true
        }
    }

    suspend fun multiEmit(){
        FakeEndpoint.fakeIntReapeatRequest().collect{

        }
    }

    fun mapLearn(): Flow<FlowModelName> {
        val model = FlowModelName("Ali Doran")
        return flow { emit(model) }
    }
}