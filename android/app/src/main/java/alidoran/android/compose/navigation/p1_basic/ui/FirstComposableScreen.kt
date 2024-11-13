package alidoran.android.compose.navigation.p1_basic.ui

import alidoran.android.compose.navigation.p1_basic.ComposeNavigationRoutes.*
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
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

@Composable
fun FirstComposableScreen(navController: NavController) {
    KotlinReferencesTheme {
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
                onValueChange = { newText -> text = newText },
                label = { Text("Enter your text") }
            )
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Go to previous page")
            }
            Button(
                onClick = { navController.navigate(SecondScreen.name) }) {
                Text(text = "Go to the SecondScreen")
            }
            Button(onClick = { navController.navigate("${ThirdScreen.name}/$text") }) {
                Text(text = "Go to third screen and pass text")
            }
            Button(onClick = { navController.navigate("${Feature1ComposeScreen.name}/$text") }) {
                Text(text = "Go to Feature1 Screen and pass text")
            }
        }
    }
}