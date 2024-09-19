package alidoran.design_pattern.kotlin.delegation

import kotlin.reflect.KProperty

class ExampleDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "Example Delegate Value"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by ExampleDelegate()
}

fun main() {
    val example = Example()
    println(example.p)  // Prints: Example Delegate Value
    example.p = "New Value"  // Prints: New Value has been assigned to 'p' in Example@<hashcode>.
}