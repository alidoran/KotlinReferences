package alidoran.apis.rest_retrofit.retro

import com.example.retrofitteach.models.Current
import com.example.retrofitteach.models.Location


interface WeatherService{
        fun location(location: Location)
        fun current(current: Current)
}

