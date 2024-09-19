package alidoran.design_pattern.kotlin.delegation

interface SoundBehavior {
    fun makeSound()
}

class Quack : SoundBehavior {
    override fun makeSound() {
        println("Quack!")
    }
}

class Squeak : SoundBehavior {
    override fun makeSound() {
        println("Squeak!")
    }
}

class Duck(private val soundBehavior: SoundBehavior) : SoundBehavior by soundBehavior

fun main() {
    val quackingDuck = Duck(Quack())
    quackingDuck.makeSound()  // Prints: Quack!

    val squeakingDuck = Duck(Squeak())
    squeakingDuck.makeSound()  // Prints: Squeak!
}