package alidoran.design_pattern.java.test

class MementoTest {
    private val data = ContentModelTest()

    fun setModel() {
        data.push("Ali")
        data.push("Roya")
        data.push("Hosain")
        data.push("Mohammad")
    }

    fun getModel() {
        println(data.pull())
        println(data.pull())
        println(data.pull())
        println(data.pull())
    }
}

fun main() {
//    val mementoTest = MementoTest()
    println(ascii(65))

}

fun ascii(asciiCode: Int): Char {
    return asciiCode.toChar()
}