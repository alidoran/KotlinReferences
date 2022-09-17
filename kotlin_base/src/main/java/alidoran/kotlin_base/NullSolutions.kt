package alidoran.kotlin_base

class NullSolutions {
    //if null fill with other thing
    fun ifNull() {
        val variable: String? = null
        val printString = variable ?: "It is null"
        println(printString)
    }

    fun ifElseKotlin(){
        val printString: String =
            when(val variable: String? = null) {
            null -> "It is null"
            else -> "It is $variable"
        }
        println(printString)
    }

    fun ifElseLet(){
        val variable: String? = null
        variable.let { if (it != null) println(it) else println("It is null") }
    }

    fun ifElseWith(){
        val variable: String? = null
        variable.let { if (it != null) println(it) else println("It is null") }
    }

    fun ifElseAlso(){
        val variable: String? = null
        variable?.also { println(it) } ?: println("It is null")
    }
}