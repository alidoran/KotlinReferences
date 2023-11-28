package alidoran.android.compose.advance.stateful_composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OuterComposableScreen(
    viewModel: OuterViewModel,
    innerViewModel: InnerViewModel
){
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val complexData by innerViewModel.innerComplexData.collectAsStateWithLifecycle()
        Button(
            onClick = {
                val currentAge = complexData.age
                innerViewModel.updateInnerComplexData(viewModel.generateInitialModel(currentAge))
            }) {
            Text(text = "ChangeComplexValues")
        }
        InnerComposable(innerViewModel)
    }
}