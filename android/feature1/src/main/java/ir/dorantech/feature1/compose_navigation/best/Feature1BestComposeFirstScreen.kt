package ir.dorantech.feature1.compose_navigation.best

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Feature1BestComposeFirstScreen(
    onFirstFeatureSecondScreenClick: (text: String) -> Unit,
    onSecondFeatureScreenClick: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") }
        )
        Button(onClick = {
            onFirstFeatureSecondScreenClick(text)
        }) {
            Text(text = "Go to first feature second screen")
        }
        Button(onClick = { onSecondFeatureScreenClick(text) }) {
            Text(text = "Go to second feature screen")
        }
    }
}

@Composable
fun Feature1BestComposeSecondScreen(
    onFirstFeatureFirstScreenClick: (text: String) -> Unit,
    onSecondFeatureScreenClick: (text: String) -> Unit,
    inputText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        Text(text = inputText)
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") }
        )
        Button(onClick = {
            onFirstFeatureFirstScreenClick(text)
        }) {
            Text(text = "Go to first feature first screen")
        }
        Button(onClick = {
            onSecondFeatureScreenClick(text)
        }) {
            Text(text = "Go to second feature screen")
        }
    }
}
