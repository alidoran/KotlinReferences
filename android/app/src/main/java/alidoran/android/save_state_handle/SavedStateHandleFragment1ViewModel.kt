package alidoran.android.save_state_handle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SavedStateHandleFragment1ViewModel(
    private val state: SavedStateHandle
) : ViewModel() {
    private var _counterViewModelLiveData = MutableLiveData(0)
    val counterViewModelLiveData
        get() = _counterViewModelLiveData

    private var _countSavedStateHandle = state.getLiveData("count", 0)
    val countSavedStateHandle
        get() = _countSavedStateHandle
    private var jobVm: Job? = null
    private var jobSavedStateHandle: Job? = null

    fun startVmCounter() {
        jobVm?.cancel()
        jobVm = viewModelScope.launch {
            while (true) {
                delay(TimeUnit.SECONDS.toMillis(1))
                _counterViewModelLiveData.value = _counterViewModelLiveData.value!! + 1
            }
        }
    }

    fun startSavedStateHandleCounter() {
        jobSavedStateHandle?.cancel()
        jobSavedStateHandle = viewModelScope.launch {
            while (true) {
                delay(TimeUnit.SECONDS.toMillis(1))
                _countSavedStateHandle.value = _countSavedStateHandle.value!! + 1
                state["count"] = _countSavedStateHandle.value
            }
        }
    }

}