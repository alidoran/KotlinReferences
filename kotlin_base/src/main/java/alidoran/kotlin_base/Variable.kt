package ir.alidoran.teach_kotlin

object Variable {
    var counter = 0
    private val valValue: Int
        get() = counter++

    @JvmStatic
    fun main(args: Array<String>) {
        LazyInitial().lazyUse()
    }

    private fun changeValGet() {
        println(valValue)
        println(valValue)
        println(valValue)
    }

    private fun varType() {
        var varDefinedType: String
        var varUndefinedType = "firstValue"
        varDefinedType = "secondValue"
        varUndefinedType = "secondValue"
    }

    private fun nullVariable() {
        val notNull: String = "cant store null"
        val canNull: String? = null
        notNull.length
//        canNull.length "this has an error because this extension doesn't accept null variable"
        val nullValLengthByIf = if (canNull != null) canNull.length else null
        val nullValLengthBySafeAccess = canNull?.length //Safe Access is like to above If

        canNull ?: 3 //If canNull == null answer is three else answer is canNull

        canNull!!         //Convert nullable variable to not nullable variable
        canNull.length


        if (canNull == null) return  //Compiler flow variable
        canNull.length
    }

    private fun valType() {
        val varDefinedType: String
        val varUndefinedType = "firstValue"
        varDefinedType = "secondValue"
        //Error Situation
//        varDefinedType = "third"
//        varUndefinedType = "secondValue"
        val pointerMode = arrayListOf("firstValue")
        pointerMode.add("secondValue")

        val simpleList = listOf("firstValue")
        //Error Situation
//        simpleList.add("secondValue")
    }

    private fun castMethod(any: Any) {
        if (any is String)
            any.lastChar()
        (any as? String)?.lastChar() //Safe Cast return null if can't cast to String
    }

    private fun valSetup() {
        class SampleModel {
            private lateinit var value: String

            fun setup() {
                value = "default"
            }

            fun display() {
                println(value)
            }
        }

        val callSample = SampleModel()
        //callSample.display() // show error
        callSample.setup()
        callSample.display()
    }

    class Equal {
        init {
            println(equals1(Value("abc"), Value("abc")))
            println(equals1(Value("abc"), null))
            println(equals1(null, Value("abc")))
            println(equals1(null, null))
            println("\n")
            println(equals2(Value("abc"), Value("abc")))
            println(equals2(Value("abc"), null))
            println(equals2(null, Value("abc")))
            println(equals2(null, null))
        }

        data class Value(val s: String)

        private fun equals1(v1: Value?, v2: Value?): Boolean = v1 == v2

        private fun equals2(v1: Value?, v2: Value?): Boolean = v1?.equals(v2) ?: (v2 === null)
    }

    class LazyInitial {
        private val pi: Float by lazy { 3.14f }
        fun lazyUse() {
            println("Lazy isn't initialize")
            val result = pi
            println(pi.toString())
        }
    }
}