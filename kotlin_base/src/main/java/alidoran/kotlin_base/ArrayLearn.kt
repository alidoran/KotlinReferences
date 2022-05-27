package ir.alidoran.teach_kotlin

object ArrayLearn {

    val list = listOf("a","b","c")

    @JvmStatic
    fun main(args: Array<String>) {
        intArrayType()
    }

    fun getOrNull(){
        println(list.getOrNull(0))
        println(list.getOrNull(1))
        println(list.getOrNull(3))
    }

    fun withIndex(){
        for ((index , element) in list.withIndex()){
            println("$index = $element")
        }
    }

    fun intArrayType(){
        var a = arrayOf(3, 2, 5)
        var b: List<Int> = listOf(3, 2, 5)
        var c: IntArray = intArrayOf(3,2,5)
        var d: IntRange = 1..100
    }

    private fun listReferencesOrNewOne(){
        val list = ArrayList(arrayListOf(1,2,3))
        val newList = list + 4
        println(list)
        println(newList)

        val mutableList = ArrayList(mutableListOf(4,5,6))
        val newMutableList = mutableList
        newMutableList.add(7)
        println(mutableList)
        println(newMutableList)
    }
}