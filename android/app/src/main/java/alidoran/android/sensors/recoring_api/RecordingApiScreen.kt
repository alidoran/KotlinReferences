package alidoran.android.sensors.recoring_api

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

context(Context)
@Composable
fun RecordingApiScreen(
    onCountReadClick: () -> StateFlow<String>,
    onStopClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var flow: StateFlow<String>? by rememberSaveable { mutableStateOf(null) }
    val text by flow?.collectAsState(initial = "Initial Text") ?: rememberSaveable { mutableStateOf("Loading") }

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = text)
        Button(onClick = {
            flow = onCountReadClick.invoke()
        }) {
            Text("ReadData")
        }
        Button(onClick = onStopClick) {
            Text("Stop")
        }
    }
}