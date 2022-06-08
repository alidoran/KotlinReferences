package alidoran.di.singleton_injection

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

class EngineSingleton @Inject constructor(){
val type = "MyType"
}

@Singleton
class CarSingleton @Inject constructor(engineSingleton: EngineSingleton){
    init {
        println("singleton by ${engineSingleton.type} was created")
    }
}

@Singleton
@Component
interface CarSingletonComponent{
    fun getCar(): CarSingleton
    fun inject(singletonActivity: SingletonActivity)
}