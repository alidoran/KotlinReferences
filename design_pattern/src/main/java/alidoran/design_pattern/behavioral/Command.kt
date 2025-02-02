package alidoran.design_pattern.behavioral

interface Command {
    fun execute()
}

class Light {
    fun turnOn() = println("Light is ON")
    fun turnOff() = println("Light is OFF")
}

class TurnOnCommand(private val light: Light) : Command {
    override fun execute() = light.turnOn()
}

fun main() {
    val light = Light()
    val turnOn = TurnOnCommand(light)

    turnOn.execute() // Light is ON
}
