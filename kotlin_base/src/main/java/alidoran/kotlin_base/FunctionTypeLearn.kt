package ir.alidoran.teach_kotlin

class FunctionTypeLearn {

    fun functionType() {
        val sumIntTypeOld: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        val sumIntType = { x: Int, y: Int -> x + y }
        println(sumIntType(1, 2))

        val isEvenOld1: (Int) -> Boolean = { i: Int -> i % 2 == 0 }
        val isEven = { i: Int -> i % 2 == 0 }
        kotlin.run { println(isEven) }

        val list = listOf(1, 2, 3, 4)
        list.any(isEven) //result = true
        list.filter(isEven) //result = [2,4]

        run { println("hey") }
    }

    fun nullableDifferent() {
//        val f1: () -> Int? = null
        val f2: () -> Int? = { null }
        val f3: (() -> Int)? = null
//        val f4: (() -> Int)? = { null }
    }

    fun functionTypeVsFunctionReference() {

        //functionType
        val isEvenLambda = { i: Int -> i % 2 == 0 }
        val predicateLambda = isEvenLambda

        //FunctionReference
        fun isEvenMethod(i: Int): Boolean = i % 2 == 0
        //val predicate = isEven //Compiler Error
        val predicateMethod = ::isEvenMethod
        val predicateMethod2 = { i: Int -> isEvenMethod(i) }


        val list = listOf(1, 2, 3, 4)

        //functionType
        list.any(isEvenLambda)    //result = true
        list.filter(isEvenLambda) //result = [2,4]

        //FunctionReference
        list.any(::isEvenMethod)    //result = true
        list.filter(::isEvenMethod) //result = [2,4]
    }
}