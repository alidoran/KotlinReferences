package android.test.mockito.simple_mockito

object AddMockObject {
    @JvmStatic
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}

object FirstObject {
    fun firstMethod() {
        SecondObject.secondMethod()
    }
}

object SecondObject {
    fun secondMethod() {
        println()
    }
}

class ObjectClass{
    fun firstMethod() {
        SecondObject.secondMethod()
    }
}