package doran.ali.junit5

class AssertionMainMethod {
    fun isTrue (falseNum: Int): Boolean{
        return when (falseNum) {
            1 -> true
            0 -> false
            else -> throw NumberFormatException("This isn't valid number")
        }
    }
}