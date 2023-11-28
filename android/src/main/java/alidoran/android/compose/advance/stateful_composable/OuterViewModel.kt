package alidoran.android.compose.advance.stateful_composable

import androidx.lifecycle.ViewModel

class OuterViewModel: ViewModel() {
    fun generateInitialModel(currentAge: Int)= InnerViewModel.InitialModel("Ali", currentAge + 1)
}