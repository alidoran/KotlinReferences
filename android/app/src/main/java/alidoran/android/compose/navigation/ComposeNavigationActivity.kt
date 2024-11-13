package alidoran.android.compose.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.dorantech.feature1.compose_navigation.Feature1ComposeScreen

class ComposeNavigationActivity : ComponentActivity() {
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
    /*
     NavHost:
     The NavHost is the container where all the destinations (composables/screens) are defined.
     It holds the navigation graph, managing which composable is shown based on the navigation state.
    */
    NavHost(
        /*
        NavController:
        The NavController is responsible for navigating between destinations.
        It keeps track of the back stack, handles navigation actions (e.g., navigate, pop), and
        provides APIs to interact with the navigation graph.
        */
        navController = navController,
        startDestination = ChooseScreen.FistScreen.name
    ) {
        /*
        composable:
        This function defines a destination in the NavHost.
        It takes a route (like a screen name) and a composable function that represents the screen UI.
        Each destination can optionally take arguments, which are extracted from the route.
        */
        composable(ChooseScreen.FistScreen.name) {
            FistComposableScreen(navigation = navController)
        }
        composable(ChooseScreen.SecondScreen.name) {
            SecondComposableScreen(navigation = navController)
        }
        composable("${ChooseScreen.ThirdScreen}.name/{text}") { backStackEntry ->
            ThirdComposableScreen(
                navigation = navController,
                text = backStackEntry.arguments?.getString("text").orEmpty()
            )
        }
        composable("{ChooseScreen.Feature1ComposeScreen.name}/{text}") { backStackEntry ->
            Feature1ComposeScreen(
                navigation = navController,
                text = backStackEntry.arguments?.getString("text").orEmpty()
            )
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
    SecondScreen,
    ThirdScreen,
    Feature1ComposeScreen,
    Feature2ComposeScreen,
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
            var text by remember { mutableStateOf("") }

            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Enter your text") }
            )
            Button(
                onClick = { navigation.navigate(ChooseScreen.SecondScreen.name) }) {
                Text(text = "Go to the SecondScreen")
            }
            Button(onClick = { navigation.popBackStack() }) {
                Text(text = "Go to previous page")
            }
            Button(onClick = { navigation.navigate("${ChooseScreen.ThirdScreen.name}/$text") }) {
                Text(text = "Go to third screen and pass text")
            }
            Button(onClick = { navigation.navigate("${ChooseScreen.Feature1ComposeScreen.name}/$text") }) {
                Text(text = "Go to Feature1 Screen and pass text")
            }
        }
    }
}

@Composable
private fun SecondComposableScreen(navigation: NavController) {
    KotlinReferencesTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navigation.navigate(ChooseScreen.FistScreen.name) }) {
                Text(text = "Go to the FirstScreen")
            }
            Button(onClick = { navigation.popBackStack() }) {
                Text(text = "Go to previous page")
            }
            Button(onClick = { navigation.popBackStack(ChooseScreen.FistScreen.name, false) }) {
                Text(text = "Go to First page and delete stack except of current")
            }
        }
    }
}

@Composable
private fun ThirdComposableScreen(navigation: NavController, text: String) {
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
                onClick = { navigation.navigate(ChooseScreen.SecondScreen.name) }) {
                Text(text = "Go to the SecondScreen")
            }
            Button(onClick = { navigation.popBackStack() }) {
                Text(text = "Go to previous page")
            }
        }
    }
}