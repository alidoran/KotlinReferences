package doran.ali.junit5

import kotlin.IllegalArgumentException

class Calculator {
    fun add(firstNum: Int, SecondNum: Int): Int {
        //red
        //return 0
        //green
        return firstNum+SecondNum
    }

    fun sub(firstNum: Int, secondNum: Int): Int {
        return firstNum-secondNum
    }

    fun divide(firstNum: Int, secondNum: Int): Int {
        if (secondNum==0) throw IllegalArgumentException("you can not dividing to zero")
        return firstNum/secondNum
    }

    fun multiplySix(inputNum:Int): Int{
        return inputNum*6
    }
}