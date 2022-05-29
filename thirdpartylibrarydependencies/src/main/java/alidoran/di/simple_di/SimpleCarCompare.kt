package alidoran.di.simple_di

class WithoutDISimple {
    fun drive() {
        if(EngineSimple().start())
        println("CarWithoutDI Driving...")
    }
}

class WithDISimple(private val engineSimple: EngineSimple) {
    fun drive() {
        if (engineSimple.start())
        println("CarWithDI driving...")
    }
}

    //region engine
open class EngineSimple {
    open fun start():Boolean {
        println("Engine started")
        return true
    }
}

class ElectricEngineSimple : EngineSimple() {
    init {
        println("Electric engine selected")
    }
}

class GasEngineSimple : EngineSimple() {
    init {
        println("Gas engine selected")
    }
}
    //endregion