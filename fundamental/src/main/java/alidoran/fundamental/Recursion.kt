package com.example.fundamental

object Recursion {

    @JvmStatic
    fun main(args: Array<String>) {
        sumNaturalNum(10)
    }

    private fun reverseString(myString: String): String {
        /*  The main point in recursion methods is results will be stacked in memory.
        The result is delivered after final result completely   */
        if (myString == "")
            return ""
        else {
            return reverseString(myString.substring(1)) + myString[0]
        }
    }

    private fun isMirror(myString: String): Boolean {
        if (myString.length <= 1)
            return true
        return if (myString.first() == myString.last()) {
            isMirror(myString.substring(1, myString.lastIndex))
        } else
            return false
    }

    private fun decimalToBinary(myInt: String): String {
        if (myInt.toInt() < 2)
            return myInt
        return decimalToBinary((myInt.toInt() / 2).toString()) + myInt.toInt().rem(2).toString()
    }

    private fun decimalToBinaryTwoInput(myNumber: Int, result: String): String {
        var result = result
        if (myNumber == 0) {
            return result
        }
        result = myNumber.rem(2).toString() + result
        return decimalToBinaryTwoInput(myNumber / 2, result)
    }

    private fun sumNaturalNum(myNum:Int):Int{
        if (myNum<=1)
            return myNum
        return myNum+ sumNaturalNum(myNum-1)
    }
}
