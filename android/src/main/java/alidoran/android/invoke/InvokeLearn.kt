package alidoran.android.invoke

class InvokeLearn {
    operator fun invoke(): String {
        // do stuff
        return "AliDoran"
    }
}

fun main() {
    val config = InvokeLearn()
    println(config())
}

