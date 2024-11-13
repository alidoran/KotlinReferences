package ir.dorantech.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Feature1CleanComposeFirstScreen: Route

    @Serializable
    data class Feature1CleanComposeSecondScreen(val text: String): Route

    @Serializable
    data class Feature2CleanComposeScreen(val text: String): Route
}