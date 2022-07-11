package alidoran.third_party.di.simple_di

const val cheapAirCondition = true

class AirConditionFactory{
    private val airConditionEngine = AirConditionEngineFactory()
    fun get()= AirCondition(airConditionEngine.get())
}

class AirConditionEngineFactory{
    private val wireFactory = WireFactory()
    fun get()= AirConditionEngine(wireFactory.get())
}

class WireFactory{
    fun get() = if (cheapAirCondition){
        Wire("Iron" ,"Plastic" )
    }else{
        Wire("Copper", "Plastic")
    }
}



data class Wire(val core:String,val skin:String)

data class AirConditionEngine(val wire: Wire)

class AirCondition(val engine: AirConditionEngine){
    fun start(){
        println("Engine Started")
    }
}