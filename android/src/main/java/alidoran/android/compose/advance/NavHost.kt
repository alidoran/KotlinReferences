package alidoran.android.compose.advance

import alidoran.android.compose.advance.datasource.InMemoryUserDataSource
import alidoran.android.compose.advance.datasource.UserDataSource
import alidoran.android.compose.advance.datasource.UserListDataSourceScreen
import alidoran.android.compose.advance.viewmoel.ComposeViewModel
import alidoran.android.compose.advance.viewmoel.FeatureListScreen
import alidoran.android.compose.advance.viewmoel.NavigationScreen
import alidoran.android.compose.advance.viewmoel.ViewModelHoistingScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ProjectNavHost() {
    val navController = rememberNavController()
    val dataSource: UserDataSource = InMemoryUserDataSource()
    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        navController = navController,
        startDestination = ChooseScreen.FeatureListScreen.name,
    ) {
        composable(ChooseScreen.FeatureListScreen.name) {
            FeatureListScreen(navController)
        }
        composable(ChooseScreen.ViewModelHoistingScreen.name) {
            ViewModelHoistingScreen(ComposeViewModel(), navigation = navController)
        }
        composable(ChooseScreen.CoroutinesInComposeScreen.name) { CoroutinesOnComposeScreen() }
        composable(ChooseScreen.DataSourceUserListScreen.name) { UserListDataSourceScreen(dataSource) }
        composable(ChooseScreen.PreviewsScreen.name) { ComposeForPreviewScreen() }
        composable(ChooseScreen.AnimationScreen.name) { AnimationComposeScreen(navController) }
        composable(ChooseScreen.NavigationScreen.name) {
            NavigationScreen (navController){ navController.popBackStack() }
        }
        navArgument("First Argument") { type = NavType.BoolType }
    }
}

enum class ChooseScreen {
    FeatureListScreen,
    ViewModelHoistingScreen,
    CoroutinesInComposeScreen,
    DataSourceUserListScreen,
    PreviewsScreen,
    AnimationScreen,
    NavigationScreen
}