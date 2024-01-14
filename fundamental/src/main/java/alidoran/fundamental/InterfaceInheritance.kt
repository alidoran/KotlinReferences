package alidoran.fundamental

import android.util.Log

object InterfaceInheritance {
    interface MyInterface {
        fun getString(): String
    }

    class MyClass1 : MyInterface {
        override fun getString(): String = "My Class 1"
    }

    class MyClass2 : MyInterface {
        override fun getString(): String = "My Class 2"
    }

    class MyMainClass(private val myInterface: MyInterface) {
        fun getString() = Log.d("TAG", "getInterface: ${myInterface.getString()}")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = MyMainClass(MyClass1()).getString()
        val b = MyMainClass(MyClass2()).getString()
    }
}