package alidoran.kotlin_base

import kotlin.properties.Delegates

val message: String by lazy {
    println("Initializing message...")
    "Hello, Kotlin!"
}

//Lazy
fun lazy() {
    println("Before accessing message")
    println(message)  // Triggers initialization
    println(message)  // Uses cached value
}

//observable
private var username: String by Delegates.observable("Guest") { prop, old, new ->
    println("Changed ${prop.name} from '$old' to '$new'")
}

fun observable() {
    username = "Ali"
    username = "Doran"
}

//vetoable
var age: Int by Delegates.vetoable(18) { _, old, new ->
    new >= 18 // Accept only if age is 18 or older
}

fun vetoable() {
    println("Initial age: $age")
    age = 20  // Allowed
    println("Updated age: $age")
    age = 16  // Rejected
    println("Final age: $age")
}




fun main() {
    lazy()
    observable()
    vetoable()
}