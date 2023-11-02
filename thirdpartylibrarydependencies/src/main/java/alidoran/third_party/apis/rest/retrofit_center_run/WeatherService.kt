package alidoran.third_party.apis.rest.retrofit_center_run

import alidoran.third_party.apis.rest.models.Current
import alidoran.third_party.apis.rest.models.Location


interface WeatherService {
    fun location(location: Location)
    fun current(current: Current)
}

