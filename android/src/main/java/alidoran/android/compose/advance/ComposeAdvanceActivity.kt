package alidoran.android.compose.advance


import alidoran.android.compose.advance.datasource.InMemoryUserDataSource
import alidoran.android.compose.advance.datasource.UserDataSource
import alidoran.android.compose.advance.datasource.UserListDataSourceScreen
import alidoran.android.compose.advance.stateful_composable.InnerViewModel
import alidoran.android.compose.advance.stateful_composable.OuterComposableScreen
import alidoran.android.compose.advance.stateful_composable.OuterViewModel
import alidoran.android.compose.advance.viewmoel.ComposeViewModel
import alidoran.android.compose.advance.viewmoel.FeatureListScreen
import alidoran.android.compose.advance.viewmoel.ViewModelHoistingScreen
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class ComposeAdvanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        NavigationCompose()
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationCompose() {
    val navController = rememberNavController()
    val dataSource: UserDataSource = InMemoryUserDataSource()
    NavHost(
        navController = navController,
        startDestination = ChooseScreen.FeatureListScreen.name,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(ChooseScreen.FeatureListScreen.name) {
            FeatureListScreen(navigation = navController)
        }
        composable(ChooseScreen.ViewModelHoistingScreen.name) {
            ViewModelHoistingScreen(ComposeViewModel(), navigation = navController)
        }
        composable(ChooseScreen.CoroutinesInComposeScreen.name) {
            CoroutinesOnComposeScreen()
        }
        composable(ChooseScreen.StatefulComposableScreen.name) {
            OuterComposableScreen(OuterViewModel(), InnerViewModel())
        }
        composable(ChooseScreen.DataSourceUserListScreen.name) {
            UserListDataSourceScreen(dataSource)
        }
        composable(ChooseScreen.PreviewsScreen.name) {
            ComposeForPreviewScreen()
        }
        composable(
            route = ChooseScreen.AnimationScreen.name,
        ) {
            AnimationComposeScreen(navController)
        }
    }
}

enum class ChooseScreen {
    FeatureListScreen,
    ViewModelHoistingScreen,
    CoroutinesInComposeScreen,
    StatefulComposableScreen,
    DataSourceUserListScreen,
    PreviewsScreen,
    AnimationScreen,
}