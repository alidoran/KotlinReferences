package alidoran.android.compose.navigation.p1_basic

import alidoran.android.compose.navigation.p1_basic.ComposeNavigationRoutes.*
import alidoran.android.compose.navigation.p1_basic.ui.FirstComposableScreen
import alidoran.android.compose.navigation.p1_basic.ui.SecondComposableScreen
import alidoran.android.compose.navigation.p1_basic.ui.ThirdComposableScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.dorantech.feature1.compose_navigation.basic.Feature1ComposeScreen

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
        startDestination = FirstScreen.name
    ) {
        /*
        composable:
        This function defines a destination in the NavHost.
        It takes a route (like a screen name) and a composable function that represents the screen UI.
        Each destination can optionally take arguments, which are extracted from the route.
        */
        composable(FirstScreen.name) {
            FirstComposableScreen(navController = navController)
        }
        composable(SecondScreen.name) {
            SecondComposableScreen(navController = navController)
        }
        composable("${ThirdScreen.name}/{text}") { backStackEntry ->
            ThirdComposableScreen(
                navigation = navController,
                text = backStackEntry.arguments?.getString("text").orEmpty()
            )
        }
        composable("${Feature1ComposeScreen.name}/{text}") { backStackEntry ->
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






