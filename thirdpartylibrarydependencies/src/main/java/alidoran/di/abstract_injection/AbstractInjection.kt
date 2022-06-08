package alidoran.di.abstract_injection

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Component(modules = [TubelessTireAbstractInjectionModule::class])
interface CarAbstractInjectionComponent {
    fun inject(AbstractInjectionActivity: AbstractInjectionActivity)
}

interface TireAbstractInject{
    fun getTireType(): String
}

class TubelessTireAbstractInjection @Inject constructor() : TireAbstractInject {
    override fun getTireType(): String {
        return "Abstract injection Tubeless"
    }
}


class TubeTireAbstractInjection @Inject constructor(): TireAbstractInject {
    override fun getTireType(): String {
        return "Abstract injection Tube"
    }
}

class CarAbstractInject @Inject constructor(private val tire: TireAbstractInject) {
    fun start() {
        println("Abstract injection tire started by ${tire.getTireType()}")
    }
}

@Module
abstract class TubelessTireAbstractInjectionModule{
    @Binds
    abstract fun provideTubelessTireAbstractInjection(tubelessTireAbstractInjection: TubelessTireAbstractInjection) : TireAbstractInject
}


