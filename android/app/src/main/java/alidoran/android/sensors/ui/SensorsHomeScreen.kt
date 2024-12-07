package alidoran.android.sensors.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SensorsHomeScreen(
    onStepCounterSensorClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onStepCounterSensorClick) {
            Text("StepCounterSensor")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsHomeScreenPreview() {
    SensorsHomeScreen(
        onStepCounterSensorClick = {},
    )
}