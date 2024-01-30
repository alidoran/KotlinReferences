package alidoran.android.invoke

class InvokeLearnByParameter {
    var word = ""
    operator fun invoke(s: String): InvokeLearnByParameter {
        word += s
        return this
    }
}

fun main() {
    val config = InvokeLearnByParameter()
    config("A")("l")("i")("D")("o")("r")("a")("n")
    println(config.word)
}