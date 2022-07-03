package alidoran.kotlin_base

import java.lang.StringBuilder

object LambdaWithReceiver {
    var sb = StringBuilder()

    @JvmStatic
    fun main(args: Array<String>) {
        applyLearn()
    }

    private fun withLearn() {
        //with can't be nullable
        //region Old Method
        sb.append("Valid Number is: ")
        for (c in 1..9)
            sb.append(c)
        sb.toString()
        //endregion

        //region New method
        with(sb) {
            append("Valid Number is: ")
            for (c in 1..9)
                append(c)
            toString()
        }
        //endregion

        //region Last method
        val s: String = buildString {
            append("Valid Number is: ")
            for (c in 1..9)
                append(c)
        }
        //endregion
    }

    private fun runLearn(){ //Run can be nullable but run if be not null
        sb?.run {
            append("Hello")
        }
    }

    private fun applyLearn(){
        sb.apply { append("Hello Apply") }
            .also {
            println(it.toString())
        }

        val cm = sb.apply {
            append("Hello separated apply")
        }
        cm.also { println(it.toString()) }
    }


    private fun lambdaVersusFunction() {
        val isEvenFunction: (Int) -> Boolean = { it % 2 == 0 }
        val isEvenLambda: Int.() -> Boolean = { this % 2 == 0 }
        isEvenFunction(2)
        1.isEvenLambda()
    }



}