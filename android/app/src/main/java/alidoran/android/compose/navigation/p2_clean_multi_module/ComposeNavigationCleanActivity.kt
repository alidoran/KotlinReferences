package alidoran.android.compose.navigation.p2_clean_multi_module

import android.os.Bundle
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ir.dorantech.feature1.compose_navigation.presentation.feature1CleanComposeNav
import ir.dorantech.feature2.compose_navigation.presentation.feature2CleanComposeNav
import ir.dorantech.navigation.Route

class ComposeNavigationCleanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                Column {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.Feature1CleanComposeFirstScreen
                    ) {
                        feature1CleanComposeNav(navController)
                        feature2CleanComposeNav(navController)
                    }
                }
            }
        }
    }
}
