package android.test.mockito.simple_mockito

class First{
    fun methodFirst1(){
        Second().methodSecond1()
    }

    fun methodFirst2(){
        Second().methodSecond2()
    }
}

class Second{
    fun methodSecond1(){
        println()
    }

    fun methodSecond2(){
        println()
    }
}