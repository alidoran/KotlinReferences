package ir.alidoran.teach_kotlin

class Conditions {

    fun ifCondition() {
        var a = 5
        var b = 10
        var c = if (a > b) a else b
    }


    enum class Color {
        Black, Blue, Yellow, White, Transparent
    }

    fun simpleWhen(color: Color): String =
        when (color) {
            Color.Black -> "The color is Black"
            Color.Yellow -> "The color is Yellow"
            Color.Blue -> "The color is Blue"
            Color.White -> "The color is White"
            else -> "I cant find your Color"
        }

    fun twoConditionWhen(color: Color): String =
        when (color) {
            Color.Black, Color.Blue -> "The color dark"
            Color.White, Color.Yellow -> "The color is light"
            else -> "I cant find your Color"
        }

    fun conditionMixColor(colorOne: Color, colorTwo: Color) =
        when (setOf(colorOne, colorTwo)) {
            setOf(Color.Black, Color.Blue) -> "The color dark"
            setOf(Color.White, Color.Yellow) -> "The color is light"
            else -> "Your color is mixed color"
        }

    fun getPet(pet: Pet) {
        when(pet) {
            is Cat -> pet.mio() //Like instance of in java
            is Dog -> pet.hap() //Like instance of in java
        }
    }
}

class Cat : Pet() {
    fun mio() {
        println("mio")
    }
}

class Dog : Pet() {
    fun hap() {
        println("hap")
    }
}

open class Pet {

}

