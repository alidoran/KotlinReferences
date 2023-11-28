package alidoran.android.compose.advance.stateful_composable

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun InnerComposable(innerViewModel: InnerViewModel) {
    val name by innerViewModel.innerComplexData.collectAsStateWithLifecycle()
    val age by innerViewModel.innerComplexData.collectAsStateWithLifecycle()
    Log.d("TAG", "InnerComposable: Name:$name Age: $age")
}