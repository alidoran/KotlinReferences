package alidoran.android.compose.navigation.p3_best_approach_multi_module

import android.os.Bundle
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.dorantech.feature1.compose_navigation.best.Feature1BestComposeFirstScreen
import ir.dorantech.feature1.compose_navigation.best.Feature1BestComposeSecondScreen
import ir.dorantech.feature2.compose_navigation.best.Feature2BestComposeScreen

class ComposeNavigationBestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                Column {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = RouteBest.Feature1BestComposeFirstScreen
                    ) {
                        composable<RouteBest.Feature1BestComposeFirstScreen> { backStackEntry ->
                            Feature1BestComposeFirstScreen(
                                onFirstFeatureSecondScreenClick = { text ->
                                    navController.navigate(RouteBest.Feature1BestComposeSecondScreen(
                                        text = text
                                    ))
                                }, onSecondFeatureScreenClick = {
                                    navController.navigate(RouteBest.Feature2BestComposeScreen())
                                }
                            )
                        }

                        composable<RouteBest.Feature1BestComposeSecondScreen> { backStackEntry ->
                            Feature1BestComposeSecondScreen(
                                onFirstFeatureFirstScreenClick = {
                                    navController.navigate(RouteBest.Feature1BestComposeFirstScreen)
                                }, onSecondFeatureScreenClick = { text ->
                                    navController.navigate(RouteBest.Feature2BestComposeScreen(text))
                                },
                                inputText = backStackEntry.arguments?.getString("text").orEmpty()
                            )
                        }

                        composable<RouteBest.Feature2BestComposeScreen> { backStackEntry ->
                            Feature2BestComposeScreen(
                                onPreviousScreenClick = {
                                    navController.popBackStack()
                                },
                                text = backStackEntry.arguments?.getString("text").orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }
}
