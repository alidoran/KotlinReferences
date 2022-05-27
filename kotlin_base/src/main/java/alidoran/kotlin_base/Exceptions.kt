package ir.alidoran.teach_kotlin

import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class Exceptions {
    fun checkNumberThrow(num: Int) {
        val percentage =
            if (num in 0..100)
                println(num)
            else
                throw IllegalArgumentException(
                    "number $num is wrong"
                )
    }

    fun checkNumberTryCatch(num: Int) {
        val percentage =
            try {
                println(num.toString())
            } catch (e: NumberFormatException) {
//                null
                return
            }
    }

    @Throws(IOException::class)
    public fun checkNumberTryCatch2(num: Int) {
        println("Ali")
        var num2 = num + 2
        num2 = 999999999 * num2
        print(num2)
        throw IOException()

    }


    @Throws(IOException::class)
    fun a() {
        throw IOException()
    }
}