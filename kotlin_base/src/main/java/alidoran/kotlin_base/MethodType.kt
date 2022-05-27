//For Identify From Java Class
@file:JvmName("MethodTypeManualName")

package ir.alidoran.teach_kotlin

import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

fun outOfClassMethod() {

}

class MethodType {

    fun inClassMethod() {
        fun inMethodMethod() {

        }
    }


    fun normalMethod(a: Int, b: Int): Int {
        return a + b
    }

    fun expressionMethod(a: Int, b: Int): Int = a + b

    fun firstVoid() = println("Hi")
    fun secondVoid(): Unit = println("Hi")


    class PassMethod {

        companion object {
            @JvmStatic
            fun main(args: Array<String>) {
                MethodType.PassMethod().passedMethod()
            }
        }

        fun passedMethod() {
            acceptMethod { println("This method will run on the acceptMethod") }
        }

        fun acceptMethod(f: () -> Unit) {
            f()
        }


    }

    class InlineLearn {

        companion object {
            @JvmStatic
            fun main(args: Array<String>) {
                InlineLearn().foo(5)
            }
        }

        //region Inline Simple Sample
        val name = "AliDoran"
        fun passedMethod() {
            acceptMethod { println("passedMethod, $name") }
            acceptMethodInline { println("passedMethod, $name") }
        }

        private fun acceptMethod(f: () -> Unit) {
            f()
            println("Without inline pass the object and run")
        }

        private inline fun acceptMethodInline(f: () -> Unit) {
            f()
            println("With inline method run directly")
        }
        //endregion

        //region Inline Sample By Input
        private inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? =
            if (!predicate(this)) this else null

        fun foo(number: Int) {
            val result01 = number.takeUnless { it < 10 }
            val result02 = number.takeUnless { it > 10 }
            println("Result01 = $result01 \n Result02 = $result02 ")
        }
        //endregion
    }


}