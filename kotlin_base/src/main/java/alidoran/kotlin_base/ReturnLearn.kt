package ir.alidoran.teach_kotlin

class ReturnLearn {


    fun returnByLambda() {

        fun duplicateNonZeroIncorrect(list: List<Int>): List<Int> { //IncorrectMethod
            return list.flatMap {
                if (it == 0) return listOf()
                listOf(it, it)
            }
        }

        //SolutionUsingLabel
        fun duplicateNonZeroByLabel(list: List<Int>): List<Int> { //IncorrectMethod
            return list.flatMap ReturnLabel@{
//                if (it == 0) return@flatMap listOf() //This or next line is same
                if (it == 0) return@ReturnLabel listOf()
                listOf(it, it)
            }
        }

        //SolutionUsingLocalFunction
        fun duplicateNonZeroByLocalFunction() {
            fun duplicateNonZeroByElement(e: Int): List<Int> {
                if (e == 0) return listOf()
                return listOf(e, e)
            }
        }

        //SolutionUsingAnonymousFunction
        fun duplicateNonZeroByAnonymousFunction(list: List<Int>): List<Int> {
            return list.flatMap(fun(e): List<Int> {
                if (e == 0) return listOf()
                return listOf(e, e)
            })
        }

        fun duplicateNonZeroByNoReturn(list: List<Int>): List<Int> {
            return list.flatMap {
                if (it == 0)
                    listOf()
                else
                    listOf(it, it)
            }
        }




    val list = listOf(3, 0, 5);
    println(duplicateNonZeroIncorrect(list))
    println(duplicateNonZeroByLabel(list)) //result = []
//  ....

}


}