package alidoran.third_party.di.field_injected_class

import dagger.Component
import javax.inject.Inject

class EngineFieldInjectedClass @Inject constructor(){
    val engineType = "My Engine"
}

class CarFieldInjectedClass @Inject constructor(){

    /*
    Point is here
    We don't need add anything for engineFieldInjectedClass filling
     */
    @Inject
    lateinit var engineFieldInjectedClass: EngineFieldInjectedClass

    fun start(){
        println("Car started by ${engineFieldInjectedClass.engineType}")
    }
}

@Component
interface CarFieldInjectedClassComponent{
    fun inject(): CarFieldInjectedClass
}