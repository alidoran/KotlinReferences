@file:JvmName("ExtensionsKotlin")
package ir.alidoran.teach_kotlin

import java.math.BigInteger


fun String.lastChar() = this.get(this.length-1)
fun String.firstCharRepeat(repeatCount : Int) = get(1)
fun List<Int>.sum(): Int {
    var result = 0
    for (i in this)
        result += i
    return result
}

class Extensions {
    val c :Char = "abc".lastChar()
    val sum = listOf(1,2,3).sum()
}

class TwoSameNameExtension{

    fun List<Double>.sameName():String{
        return "ALi"
    }

    @JvmName("sameNameIntInput")
    fun List<Int>.sameName():String{
        return "Doran"
    }
}

object ExtensionSquareBracket{
    class Board(x:Int, y:Int)

    @JvmStatic
    fun main(args: Array<String>) {
        val board = Board(0,0)
        board[1,2] = "Ali Doran"
    }


    operator fun Board.set(x:Any, y:Any, name:String){
        if (x==1)
            println(name)
    }
}