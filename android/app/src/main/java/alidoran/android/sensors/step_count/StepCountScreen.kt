package alidoran.android.sensors.step_count

import StepCounter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
@Preview
fun StepCountScreen(
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val stepTaken = rememberSaveable { mutableStateOf("") }
        var countingJob by remember { mutableStateOf<Job?>(null) }

        Text(text = stepTaken.value)

        Button(
            onClick = {
                countingJob = coroutineScope.launch {
                    with(context) {
                        val stepCounter = StepCounter()
                        stepCounter.stepFlow().collect {
                            stepTaken.value = it
                        }
                    }
                }
            },
            enabled = countingJob == null
        ) {
            Text("Start Counting")
        }
        Button(
            onClick = {
                countingJob?.cancel()
                countingJob = null
            },
            enabled = countingJob != null
        ) {
            Text("Stop Counting")
        }
        Button(
            onClick = onBackClick
        ) {
            Text("Back to Sensors")
        }
    }
}