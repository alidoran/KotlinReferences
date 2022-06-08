package alidoran.di.constructor



import dagger.Component
import java.sql.DriverManager
import javax.inject.Inject

class EngineDag @Inject internal constructor() {
    init {
        DriverManager.println("Engine Dagger started")
    }
}

@Component
interface DagComponent {
    fun car(): CarDag
}

class CarDag @Inject constructor(private val engineDag: EngineDag?) {
    fun drive() {
        if (engineDag != null) DriverManager.println("Dagger Car drive...")
    }
}

private fun callDrive() {
    val car = DaggerDagComponent.create().car()
    car.drive()
}