package alidoran.kotlin_base

class Overloads {


    @JvmOverloads //OverLoad Work on java
    private fun concat(number:Int = 0, string: String = "default") :String = number.toString() +  string

    var oldConcat = concat( 10 , "First")
    var overLoadConcat = concat(10)
    var overLoadByName = concat(string = "second")

    //Error Override
//    var overLoadConcatError = concat("Second")

}




