package ir.dorantech.feature1.compose_navigation.clean

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
import androidx.navigation.NavController
import ir.dorantech.navigation.Route

@Composable
fun Feature1CleanComposeFirstScreen(navController: NavController) {
    Column(
        Modifier
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
            navController.navigate(Route.Feature1CleanComposeSecondScreen(text))
        }) {
            Text(text = "Go to first feature second screen")
        }
        Button(onClick = {
            navController.navigate(Route.Feature2CleanComposeScreen(text))
        }) {
            Text(text = "Go to second feature screen")
        }
    }
}

@Composable
fun Feature1CleanComposeSecondScreen(
    navController: NavController,
    inputText: String
) {
    Column(
        Modifier
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
            navController.navigate(Route.Feature1CleanComposeFirstScreen)
        }) {
            Text(text = "Go to first feature first screen")
        }
        Button(onClick = {
            navController.navigate(Route.Feature2CleanComposeScreen(text))
        }) {
            Text(text = "Go to second feature screen")
        }
    }
}
