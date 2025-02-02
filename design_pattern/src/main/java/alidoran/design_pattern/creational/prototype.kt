package alidoran.design_pattern.creational

data class UserPrototype(val name: String, val age: Int) {
    fun clone() = copy() // Kotlin provides a built-in copy method
}

fun main() {
    val user1 = UserPrototype("Alice", 25)
    val user2 = user1.clone()

    println(user1 == user2) // true (same data)
    println(user1 === user2) // false (different objects)
}