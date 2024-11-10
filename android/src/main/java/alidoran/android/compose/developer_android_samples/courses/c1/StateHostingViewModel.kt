package alidoran.android.compose.developer_android_samples.courses.c1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateHostingViewModel: ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun increment() {
        _counter.value += 1
    }

    /*
    val counter by rememberSaveable { viewModel.counter }
    The following code is using if we want to use the above code on UI:

    private val _counter = mutableIntStateOf(0)
    val counter: State<Int> = _counter

    fun increment() {
        _counter.intValue++
    }
     */
}