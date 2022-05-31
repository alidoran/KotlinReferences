package alidoran.di.retrofit.method_injection

import dagger.Component
import java.util.*
import javax.inject.Inject



class EngineMethodInjection @Inject()constructor(){
    val type = "mtType"
}

class CarMethodInjection @Inject constructor(private val engineMethodInjection: EngineMethodInjection) {


    /*
    The main point is here
    Remote.ProvideCar method is called automatically
     */

    @Inject
    fun provideCarMethodInjectionToRemote(remote: Remote){
        remote.provideCar(this)
    }

    fun start() = println("Car method injected with ${engineMethodInjection.type}")
}

@Component
interface CarMethodInjectionComponent {
    fun inject() : CarMethodInjection
}

class Remote @Inject constructor(){
    lateinit var car: CarMethodInjection

    fun provideCar(car: CarMethodInjection){
        println("Provide car remote")
        this.car = car
    }
}


