package alidoran.android.compose.advance.stateful_composable

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InnerViewModel: ViewModel() {
    private val _innerComplexData = MutableStateFlow(InitialModel("", 0))
    val innerComplexData = _innerComplexData.asStateFlow()

    fun updateInnerComplexData(newData: InitialModel) {
        _innerComplexData.value = newData
    }

    data class InitialModel(
        val name: String,
        val age: Int
    )
}