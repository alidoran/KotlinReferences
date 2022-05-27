package ir.alidoran.teach_kotlin

import java.lang.Appendable
import java.lang.StringBuilder

class GenericLearn {

    object TestPart {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = listOf(null, "Ali")
            list.filterNotNullable()
            appendKotlin(StringBuilder("Ali Doran"))
        }

        private fun <T : Any> List<T?>.filterNotNullable(): List<T?> {
            return this.filterNotNull()
        }

        private fun <T> appendKotlin (input:T): T where T : CharSequence, T : Appendable {
            if (!input.endsWith("."))
                input.append(" Kotlin")
            return input
        }

    }

    fun callMethods() {
        val list = listOf(null, "Ali")
        genericInput(list)
//        genericInputNotNullable(list) //It shows error due to a null attribute
    }

    private fun <T> genericInput(generic: List<T>) {}

    private fun <T : Any> genericInputNotNullable(generic: List<T>) {}
    }