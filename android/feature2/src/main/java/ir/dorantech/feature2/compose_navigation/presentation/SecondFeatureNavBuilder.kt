package ir.dorantech.feature2.compose_navigation.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ir.dorantech.feature2.compose_navigation.clean.Feature2CleanComposeScreen
import ir.dorantech.navigation.Route

fun NavGraphBuilder.feature2CleanComposeNav(navController: NavController){
    composable<Route.Feature2CleanComposeScreen>{ backStackEntry ->
        Feature2CleanComposeScreen(
            navController = navController,
            text = backStackEntry.arguments?.getString("text").orEmpty()
        )
    }
}