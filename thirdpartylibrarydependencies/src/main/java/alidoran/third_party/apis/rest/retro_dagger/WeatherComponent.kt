package alidoran.third_party.apis.rest.retro_dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface WeatherComponent {
    fun getWeatherApi(): WeatherService3
}