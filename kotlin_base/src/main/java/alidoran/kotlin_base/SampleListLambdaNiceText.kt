package ir.alidoran.teach_kotlin

class SampleListLambdaNiceText {

    fun String.isNice(): Boolean {
        val noBadSubstring =
//          !contains("ba") && !contains("be") && !contains("bu")
//          setOf("ba","be","bu").all { !this.contains(it) }
            setOf("ba", "be", "bu").none { this.contains(it) }

        val hasThreeVowels =
//            count { it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u' } >= 3
//            count { it in setOf('a', 'e', 'i', 'o', 'u') }
            count { it in "aeiou" } >= 3

//        var hasDouble = false
//        if (length > 1){
//            var prevCh : Char? = null
//            for (ch in this){
//                if (ch == prevCh)
//                    hasDouble = true
//                prevCh = ch
//            }
//        }

//        (0 until lastIndex).any { this[it] == this[it + 1] }
        var hasDouble = zipWithNext().any { it.first == it.second }

//        var condition = 0
//        if (noBadSubstring) condition++
//        if (hasThreeVowels) condition++
//        if (hasDouble) condition++
//        return condition >= 2

        return listOf(noBadSubstring, hasThreeVowels, hasDouble).count { it } >= 2
    }

}