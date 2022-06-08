package alidoran.di.interface_injection

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Component(modules = [TubelessTireInterfaceInjectionModule::class])
interface CarInterfaceInjectionComponent {
    fun inject(interfaceInjectionActivity: InterfaceInjectionActivity)
}

interface TireInterfaceInject{
    fun getTireType(): String
}

class TubelessTireInterfaceInjection @Inject constructor() : TireInterfaceInject {
    override fun getTireType(): String {
        return "Interface injection Tubeless"
    }
}


class TubeTireInterfaceInjection @Inject constructor(): TireInterfaceInject {
    override fun getTireType(): String {
        return "Interface injection Tube"
    }
}

class CarInterfaceInject @Inject constructor(private val tire: TireInterfaceInject) {
    fun start() {
        println("Interface injection tire started by ${tire.getTireType()}")
    }
}

@Module
class TubelessTireInterfaceInjectionModule{
    @Provides
    fun provideTubelessTireInterfaceInjection(tubelessTireInterfaceInjection: TubelessTireInterfaceInjection) : TireInterfaceInject {
        return tubelessTireInterfaceInjection
    }

//    @Provides
//    fun provideTubeTireInterfaceInjection(tubeTireInterfaceInjection: TubeTireInterfaceInjection) : TireInterfaceInject {
//        return tubeTireInterfaceInjection
//    }
}


/*
multi input
----------------------------------------------
 */
@Component(modules = [TubelessTireInterfaceInjectionModule::class , ElectricInterfaceInjectionModule::class])
interface MultiInterfaceInjectionComponent{
    fun inject(interfaceInjectionActivity: InterfaceInjectionActivity)
}

class CarInterfaceInjectMulti @Inject constructor(private val tire: TireInterfaceInject, private val engine: PetrolInterfaceInjection) {
    fun start() {
        println("Interface injection tire started by ${tire.getTireType()} and ${engine.getEngineType()}")
    }
}


interface EngineInterfaceInject{
    fun getEngineType(): String
}

class PetrolInterfaceInjection @Inject constructor(): EngineInterfaceInject {
    override fun getEngineType(): String {
        return "Petrol interface injection engine"
    }
}

class ElectricInterfaceInjection @Inject constructor(): EngineInterfaceInject {
    override fun getEngineType(): String {
        return "Electric interface injection engine"
    }
}

@Module
class ElectricInterfaceInjectionModule{
    @Provides
    fun providePetrolInterfaceInjection(petrolInterfaceInjection: PetrolInterfaceInjection): EngineInterfaceInject {
        return petrolInterfaceInjection
    }
}