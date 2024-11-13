package alidoran.android.compose.navigation.p1_basic.ui

import alidoran.android.compose.navigation.p1_basic.ComposeNavigationRoutes.*
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ThirdComposableScreen(navigation: NavController, text: String) {
    KotlinReferencesTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text
            )
            Button(
                onClick = { navigation.navigate(SecondScreen.name) }) {
                Text(text = "Go to the SecondScreen")
            }
            Button(onClick = { navigation.popBackStack() }) {
                Text(text = "Go to previous page")
            }
        }
    }
}