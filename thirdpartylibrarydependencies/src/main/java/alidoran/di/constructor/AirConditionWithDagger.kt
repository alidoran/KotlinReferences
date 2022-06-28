package alidoran.di.constructor


import dagger.Component
import javax.inject.Inject


@Component
interface AirConditionDagFactory {
    fun inject(): AirConditionDag
}

@Component
interface EngineBodyDagFactory {
    fun inject(): EngineBody
}

class WireDag @Inject constructor()

class EngineBody @Inject constructor(){
    val material = ""
}

data class AirConditionEngineDag @Inject constructor(val wireDag: WireDag, val engineBodyDag: EngineBody)

class AirConditionDag @Inject constructor(private val engineDag: AirConditionEngineDag) {
    fun start() {
        println("Engine Started")
    }
}

fun main() {
    DaggerAirConditionDagFactory.create().inject().start()
}

