package alidoran.design_pattern.creational

interface Car {
    fun drive()
}

class Sedan : Car {
    override fun drive() = println("Driving a sedan")
}

class SUV : Car {
    override fun drive() = println("Driving an SUV")
}

object CarFactory {
    fun createCar(type: String): Car {
        return when (type) {
            "Sedan" -> Sedan()
            "SUV" -> SUV()
            else -> throw IllegalArgumentException("Unknown car type")
        }
    }
}


fun main() {
    val sedan = CarFactory.createCar("Sedan")
    sedan.drive()  // Output: Driving a sedan

    val suv = CarFactory.createCar("SUV")
    suv.drive()  // Output: Driving an SUV
}