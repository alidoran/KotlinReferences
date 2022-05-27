package com.example.fundamental

import kotlin.math.floor

object TimeComplexity {

    //O(n)
    private fun mOn(n:Int){
        repeat (n) {
            println(1000000 * 200) //<= n(1)
            println(2000000 * 200) //<= n(1)
            println(9999999 * 200) //<= n(1)
        }
    }


    //O(n^2)
    private fun mOn2(n:Int){
        for (i in 0..n){
            for(j in 0..n){
                println("$i * $j")
            }
        }
    }

    //O(log n)
    private fun mLogN(n:Double){
        if (n == 0.0) return
        val m = floor(n/2)
        return mLogN(m)
    }




    @JvmStatic
    fun main(args: Array<String>) {
        mLogN(8.toDouble())
    }
}