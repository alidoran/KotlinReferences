package alidoran.di.retrofit

import dagger.Component
import javax.inject.Inject


@Component
interface AirConditionDagFactory {
    fun airConditionDag(): AirConditionDag
}

class WireDag @Inject constructor()

data class AirConditionEngineDag @Inject constructor(val wireDag: WireDag)

class AirConditionDag @Inject constructor(private val engineDag: AirConditionEngineDag) {
    fun start() {
        println("Engine Started")
    }
}

/*
This lines removes through Dagger
class AirConditionFactoryDag {
    private val airConditionEngine = AirConditionEngineFactory()
    fun get()= AirCondition(airConditionEngine.get())
}

class AirConditionEngineFactoryDag{
    private val wireFactory = WireFactory()
    fun get()= AirConditionEngine(wireFactory.get())
}
 */
