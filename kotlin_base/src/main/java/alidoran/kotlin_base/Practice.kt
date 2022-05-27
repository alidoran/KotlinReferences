package ir.alidoran.teach_kotlin

object Practice {

    @JvmStatic
    fun main(args: Array<String>) {
        simpleLambda(codeNameList)
    }


    val codeNameList = listOf(
        CodeNameClass(32, "Ali"),
        CodeNameClass(89, "Ozra"),
        CodeNameClass(25, "Hassan"),
        CodeNameClass(5, "Rosha")
    )

    private fun simpleLambda(codeNameList: List<CodeNameClass>) {
        val codeNameListNullAdded = ArrayList(codeNameList)
        codeNameListNullAdded.add(CodeNameClass(25, "Hosain"))
        codeNameListNullAdded.add(CodeNameClass(20, null))

        //region Sample one
        codeNameList.filter { it.code > 21 }.size
        codeNameList.count { it.code > 21 }
        //endregion

        //region Sample two
        codeNameList.sortedBy { it.code }.reversed()
        codeNameList.sortedByDescending { it.code }
//endregion

        //region sample three
        codeNameListNullAdded.map { name -> name.takeIf { it.code > 18 }?.name }.filterNotNull()
        codeNameListNullAdded.mapNotNull { name -> name.takeIf { it.code > 18 }?.name }
//endregion

        //region sample four
        codeNameListNullAdded.filterNotNull().map { it.name }
        codeNameListNullAdded.mapNotNull { it?.name }
        //endregion

        //region sample five
        val codeListMap = mutableMapOf<Int, MutableList<CodeNameClass>>()

        //first
        for (codeName in codeNameList) {
            if (codeName.code !in codeListMap)
                codeListMap[codeName.code] = mutableListOf()
            val group = codeListMap.getValue(codeName.code)
            group += codeName
        }

        //second
        for (codeName in codeNameList) {
            val group = codeListMap.getOrPut(codeName.code) { mutableListOf() }
            group += codeName
        }

        //last
        codeNameListNullAdded.groupBy { it.code }
        //endregion

        //region Sample six
        codeNameListNullAdded
            .asSequence()
            .groupBy { it.code }
            .mapValues { (_, code) -> code.size }
        val result = codeNameListNullAdded.groupingBy { it.code }.eachCount()
        //endregion
    }
}