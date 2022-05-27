package ir.alidoran.teach_kotlin

import android.widget.Switch
import androidx.core.text.isDigitsOnly

object StringLearn {
    @JvmStatic
    fun main(args: Array<String>) {
        compareString()
    }

    enum class TextChangeType{SubStringBefore
    }

    fun tripleQuoted() {
        var oldString = "hello \\ World"
        var newString = """Hello \ world"""
    }

    fun convertString() {
        "123".toInt() //123
        "1e-10".toDouble() //1.0E-10
        "xx".toInt() //NumberFormatException
        "123".toIntOrNull() //123
        "xx".toIntOrNull() //null
    }

    var pair = 'a' to 1.0 //pair is a pair value

    fun printLnAdded() {

        println(listOf("a", "b", "c").joinToString(prefix = "(", separator = "", postfix = ")"))

        val stringSampleWithMargin = """Hello,
                 World""".trimMargin(marginPrefix = "#")
        println(stringSampleWithMargin)

        val stringSampleWithoutMargin = """Hello,
                 #World""".trimIndent()
        println(stringSampleWithoutMargin)

        val stringSampleWithoutMarginBySymbol = """Hello,
                 #World""".trimMargin(marginPrefix = "#")
        println(stringSampleWithoutMarginBySymbol)

//        val regex = "\\d{2}\\.\\d{2}\\.\\d{4}".toRegex()
        val regex = """"\d{2}\.\d{2}\.\d{4}""".toRegex()
        regex.matches("15.02.2016") //true
        regex.matches("15.02.16") //false
    }

    fun zipExtension() = println("BCDF".zip("ABCD")) //[(B, A), (C, B), (D, C), (F, D)]

    fun stringMethods(changeType:TextChangeType): String{
        var text = "alidoran@gmail.com"

        when(changeType){
            TextChangeType.SubStringBefore -> return text.substringBefore('@')

        }

    }

    private fun isValidInput(inputString: String): Boolean {
        fun checkValidCharacter(ch: Char) =
            ch == '_' ||
                    /*ch in '0'..'9' || ch in 'a'..'z' || ch in 'A'..'Z'*/ ch.isLetterOrDigit()

        if (inputString.isEmpty() || inputString[0].isDigit() /*inputString[0] == '_'*/ ) return false
        for (ch in inputString)
            if (!checkValidCharacter(ch)) return false
        return true
    }

    fun testIsValidInputMethod(){
        println(isValidInput("Ali"))
        println(isValidInput("Doran"))
        println(isValidInput("1Ali"))
        println(isValidInput(""))
        println(isValidInput("A|$"))
    }

    fun separateString(){
        for (ch in "Ali")
            println(ch)
    }

    private fun compareString(){
        val a = "Ali"
        val b = "Doran"
        val c = null
        println(a>b)
        println(a<b)
        println(a==c)
        println(c==c)
    }

    private fun replaceDifferentByJava(){
        val myString= "Ali.Doran"
        myString.replace(".", "*") //myString.replace(".", "*");
        myString.replace(".".toRegex(), "*") //myString.replaceAll("\\.", "*");
    }

}
