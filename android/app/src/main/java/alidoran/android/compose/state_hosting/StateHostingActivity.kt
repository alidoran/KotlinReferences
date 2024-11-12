package alidoran.android.compose.state_hosting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/*
Usage of State Hosting table:
_____________________________________________________________________________________
| Solution             | Responsibilities                                           |
| Composables          | Simple UI-elements state management                        |
| State holder         | Complex UI-elements state management                       |
| ViewModel            | State holder for accessing business logic + screen state   |
_____________________________________________________________________________________
*/
class StateHostingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var statelessValue by rememberSaveable { mutableIntStateOf(0) }
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        StatefulCounter()
                        StatelessCounter(
                            statelessValue,
                            { statelessValue++ }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatefulCounter(
    modifier: Modifier = Modifier,
    viewModel: StateHostingViewModel = viewModel()
) {
    /* This code should use by the commented part on ViewModel
    val counter by viewModel.counter  */
    val counter by viewModel.counter.collectAsState()
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "The Stateful counter number is  $counter that shows by the Stateful")
        Button(
            onClick = { viewModel.increment() },
            enabled = counter < 10,
            modifier = Modifier.padding(top = 8.dp),
        ) { Text(text = "Add One") }
    }
}

@Composable
private fun StatelessCounter(
    counter: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Stateless Counter must be store in ViewModel
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
            StatefulCounter()
            StatelessCounter(
                0,
                {}
            )
        }
    }
}