package ir.alidoran.teach_kotlin

import android.util.Rational
import java.math.BigInteger

object Map {

    @JvmStatic
    fun main(args: Array<String>) {
        mapSetGet()
    }

    private fun mapSetGet() {
        val a = "Ali"
        val b = "Doran"
        val c = null
        val map = mutableMapOf(a to 1, b to 2)
        println(map[a])
        println(map.get(a))
        map[a] = 3
        println(map[a])
    }

    private fun likeMap(){
        val (code, name) = 1840 to "Ali"
        println(code)
        println(name)
    }
}