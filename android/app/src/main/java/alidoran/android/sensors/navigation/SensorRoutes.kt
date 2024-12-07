package alidoran.android.sensors.navigation

import kotlinx.serialization.Serializable

sealed interface SensorRoutes {
    @Serializable
    data object SensorsHome: SensorRoutes
    @Serializable
    data object StepCount: SensorRoutes
}