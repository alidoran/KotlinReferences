package alidoran.android.compose.developer_android_samples.courses.advance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class SimpleNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { NavigationCompose() }
            }
        }
    }
}

@Composable
private fun NavigationCompose() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ChooseScreen.FistScreen.name) {
        composable(ChooseScreen.FistScreen.name) {
            FistComposableScreen(navigation = navController)
        }
        composable(ChooseScreen.SecondScreen.name) {
            SecondComposableScreen(navigation = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AdvanceComposePreview() {
    KotlinReferencesTheme { NavigationCompose() }
}

private enum class ChooseScreen {
    FistScreen,
    SecondScreen
}

@Composable
private fun FistComposableScreen(navigation: NavController) {
    KotlinReferencesTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigation.navigate(ChooseScreen.SecondScreen.name) }) {
                Text(text = "Go to the SecondScreen")
            }
            Button(onClick = {navigation.popBackStack()}) {
                Text(text = "Go to previous page")
            }
        }
    }
}

@Composable
private fun SecondComposableScreen(navigation: NavController) {
    KotlinReferencesTheme {
        Column(
            Modifier.fillMaxSize().background(Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {navigation.navigate(ChooseScreen.FistScreen.name)}) {
                Text(text = "Go to the FirstScreen")
            }
            Button(onClick = {navigation.popBackStack()}) {
                Text(text = "Go to previous page")
            }
            Button(onClick = {navigation.popBackStack(ChooseScreen.FistScreen.name, false)}) {
                Text(text = "Go to First page and delete stack except of current")
            }
        }
    }
}