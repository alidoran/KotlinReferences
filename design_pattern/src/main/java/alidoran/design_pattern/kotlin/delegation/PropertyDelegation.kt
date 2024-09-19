package alidoran.design_pattern.kotlin.delegation
import kotlin.properties.Delegates

class PropertyDelegation {
    //region observable
    class User {
        var name: String by Delegates.observable("<no name>") {
                prop, old, new ->
            println("$old -> $new")
        }
    }

    fun main() {
        val user = User()
        user.name = "first"
        user.name = "second"
    }
    //endregion

    //region lazy
    class LazySample {
        val lazyValue: String by lazy {
            println("computed!")
            "Hello"
        }
    }

    fun main2() {
        val sample = LazySample()
        println(sample.lazyValue)  // First access, "computed!" and "Hello" are printed
        println(sample.lazyValue)  // Second access, only "Hello" is printed
    }
    //endregion
}