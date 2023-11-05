package alidoran.android.compose.developer_android_samples.courses.c1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class CounterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        StateHoistingCounter()
                        StatefulCounter()
                    }
                }
            }
        }
    }
}

@Composable
private fun StateHoistingCounter(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        1- var counter = 0
//        Not-Work/We have to use mutableState
//        2- var counter = mutableListOf(0)
//        Not-Work/The counter back to zero after each recomposition
//        3- var counter = remember { mutableListOf(0)}
//        Work/The counter will reset after rotation or change theme to the dark mode
//        4- var counter = rememberSaveable { mutableListOf(0) }
//          Work perfect
        var counter by rememberSaveable { mutableStateOf(0) }
        Text(text = "The StateHoisting counter number is $counter")
        Button(
            onClick = { counter++ },
            enabled = counter < 10,
            modifier = Modifier.padding(top = 8.dp)
        ) { Text(text = "Add One") }
    }
}

@Composable
private fun StatefulCounter(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(counter, onIncrement = { counter++ }, modifier)
}

@Composable
private fun StatelessCounter(counter: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "The Stateful counter number is  $counter that shows by the Stateless")
        Button(
            onClick = onIncrement,
            enabled = counter < 10,
            modifier = Modifier.padding(top = 8.dp)
        ) { Text(text = "Add One") }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatelessCounterPreview() {
    KotlinReferencesTheme {
        Column {
            StateHoistingCounter()
            StatefulCounter()
        }
    }
}