package alidoran.design_pattern.structural

interface Coffee {
    fun cost(): Double
}

class SimpleCoffee : Coffee {
    override fun cost() = 5.0
}

class MilkDecorator(private val coffee: Coffee) : Coffee {
    override fun cost() = coffee.cost() + 2.0
}

class SugarDecorator(private val coffee: Coffee) : Coffee {
    override fun cost() = coffee.cost() + 1.0
}

fun main() {
    val coffee = SugarDecorator(MilkDecorator(SimpleCoffee()))
    println("Total cost: \$${coffee.cost()}") // Total cost: $8.0
}