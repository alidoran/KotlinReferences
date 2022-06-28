package ir.alidoran.teach_kotlin

import java.util.*

class BasicTypeKotlinJavaCorresponding {
    fun compare() {
        var int: Int //java int
        var nullableInt: Int? //Integer in java
        var list: List<Int> //List<Integer> in java
        var array: Array<Int> //Integer[] in java
        var array2: IntArray //int[] in java
        var any: Any //Object in java
    }

    fun optionalLearning(inputValue: Int?) {
        //region oldMethod
        var value: Int? = 3 //result -> 3|2147483647
        value = 4 //result -> 4|5
        value = null //result -> null|2147483647
        value = inputValue
        println(value)

        val result = Optional.ofNullable(value)
            .filter { mInt: Int -> mInt % 2 == 0 }
            .map { mInt: Int -> mInt + 1 }
            .orElse(Int.MAX_VALUE)
        println(result)
        //endregion

        //region Kotlin form
        value = inputValue
        val kotlinResult: Int = value?.takeIf { it % 2 == 0 }?.let { it+1 } ?: Int.MAX_VALUE
        //endregion
    }
}