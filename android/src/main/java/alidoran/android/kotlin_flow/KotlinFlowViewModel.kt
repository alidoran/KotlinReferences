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
import kotlinx.coroutines.launch

class KotlinFlowViewModel: ViewModel() {

    fun launchFlow(methodName: String, flow: Flow<*>) {
        viewModelScope.launch {
            flow.collect { Log.d(methodName, it.toString()) }
        }
    }

    fun simpleLaunchFlow() {
        val time = FakeEndpoint.fakeRepeatCallApi()
        launchFlow("simpleLaunchFlow", time)
    }

    fun flowMap() {
        val time = FakeEndpoint.fakeRepeatCallApi()
            .map { it.toString() }
        launchFlow("flowMap", time)
    }

    fun flowFilter() {
        val time = FakeEndpoint.fakeRepeatCallApi()
            .map { it.toString() }
            .filter { it.endsWith("0") }
        launchFlow("flowFilter", time)
    }

    fun flowCatch() {
        val time = FakeEndpoint.fakeRepeatCallApi()
            .map { it.toString() }
            .catch {
                Log.d("flowCatch", "flowCatchError")
                emit("")
            }
        launchFlow("flowCatch", time)
    }

    fun mapLearn(): Flow<FlowModelName> {
        val model = FlowModelName("Ali Doran")
        return flow { emit(model) }
    }
}