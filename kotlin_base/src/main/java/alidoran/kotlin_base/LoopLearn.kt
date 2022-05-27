package ir.alidoran.teach_kotlin

import java.lang.Exception

object LoopLearn {


    @JvmStatic
    fun main(args: Array<String>) {
        loopSample()
    }


    fun loopSample() {
        var x = 1
        do {
            println(x)
            x++
        } while (x <= 3)
        x = 1
        repeat(5){
            println(x)
            x++
        }
    }

    fun inListSample() {
        val list = listOf("a", "b", "c")

        for (a in list) {
            println(a)
        }

        for ((index, value) in list.withIndex()) {
            println("$index is index of $value")
        }

        for (a in 1..5) {
            println(a.toString()) //Result is 12345
        }

        for (a in 1 until 5) {
            println(a.toString()) //Result is 1234
        }

        for (a in 9 downTo 1 step 2) {
            println(a.toString()) //Result is 97531
        }

        for (ch in "abc") {
            println(ch + 1) //result bcd
        }

        fun accessDirectValue(c: Char) {
            var isLowCase: Boolean = c in 'a'..'z'
            var isNotDigits: Boolean = c !in '0'..'9'
            var type = when (c) {
                in 'a'..'z', in 'A'..'Z' -> "isLetter"
                in '0'..'9' -> "isdigit"
                else -> "I don't know"
            }
        }
    }

    fun loopInMap() {
        val mapList = mapOf(1 to "one", 2 to "two", 3 to "three")
        for ((key, value) in mapList) {
            println("$key = $value")
        }
    }

    fun loopSpeedTest() {
        val mainList: ArrayList<Int> = ArrayList()
        val list: List<Int> = listOf(0, 1)

        for (a in 1000000 downTo 1) {
            mainList.add(a)
        }

        fun repeatTestException() {
            var answerList = ArrayList<Int>()
            var errorNum = 0
            repeat(mainList.size) {
                try {
                    answerList.add(it / list.random())
                } catch (e: Exception) {
                    errorNum++
                }
            }
        }

        fun forOnListException() {
            var answerList = ArrayList<Int>()
            var errorNum = 0
            for (a: Int in mainList) {
                try {
                    answerList.add(a / list.random())
                } catch (e: Exception) {
                    errorNum++
                }
            }
        }

        fun forEachException() {
            var answerList = ArrayList<Int>()
            var errorNum = 0
            mainList.forEach {
                try {
                    answerList.add(it / list.random())
                } catch (e: Exception) {
                    errorNum++
                }
            }
        }

        fun iteratorException() {
            var answerList = ArrayList<Int>()
            var errorNum = 0
            val listIterator = mainList.listIterator()
            while (listIterator.hasNext()) {

                try {
                    answerList.add(listIterator.next() / list.random())
                } catch (e: Exception) {
                    errorNum++
                }
            }
        }

        fun calculateTime(r: Runnable): Long {
            val i = System.nanoTime()

            r.run()

            val j = System.nanoTime()
            return (j - i) / 1000000
        }

        var mapList = mapOf("repeatTestExceptionTime" to calculateTime(::repeatTestException),
            "forOnListExceptionTime" to calculateTime(::forOnListException),
            "forEachExceptionTime" to calculateTime(::forEachException),
            "iteratorExceptionTime" to calculateTime(::iteratorException),
            "flatMapExceptionTime" to calculateTime(::iteratorException)
            )
        println(mapList)
    }


}