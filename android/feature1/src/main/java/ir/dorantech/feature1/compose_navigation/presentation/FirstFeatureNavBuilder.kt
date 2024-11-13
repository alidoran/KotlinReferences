package ir.dorantech.feature1.compose_navigation.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ir.dorantech.feature1.compose_navigation.clean.Feature1CleanComposeFirstScreen
import ir.dorantech.feature1.compose_navigation.clean.Feature1CleanComposeSecondScreen
import ir.dorantech.navigation.Route

fun NavGraphBuilder.feature1CleanComposeNav(navController: NavController) {
    composable<Route.Feature1CleanComposeFirstScreen> {
        Feature1CleanComposeFirstScreen(navController = navController)
    }
    composable<Route.Feature1CleanComposeSecondScreen> { backStackEntry ->
        Feature1CleanComposeSecondScreen(
            navController = navController,
            inputText = backStackEntry.arguments?.getString("text").orEmpty()
        )
    }
}