package alidoran.android.compose.navigation.p3_best_approach_multi_module

import kotlinx.serialization.Serializable

sealed interface RouteBest {
    @Serializable
    data object Feature1BestComposeFirstScreen: RouteBest

    @Serializable
    data class Feature1BestComposeSecondScreen(val text: String = ""): RouteBest

    @Serializable
    data class Feature2BestComposeScreen(val text: String = ""): RouteBest
}