package alidoran.di.retrofit.interface_injection

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
    fun provideTubelessTireInterfaceInjection(tubelessTireInterfaceInjection: TubelessTireInterfaceInjection) : TireInterfaceInject{
        return tubelessTireInterfaceInjection
    }
}