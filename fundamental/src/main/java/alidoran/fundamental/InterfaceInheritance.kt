package alidoran.fundamental

object InterfaceInheritance {
    interface MyInterface {
        fun getString():String
    }

    class MyClass1: MyInterface{
        override fun getString(): String {
            return "My Class 1"
        }
    }
    class MyClass2: MyInterface{
        override fun getString(): String {
            return "My Class 1"
        }
    }

    class MyMainClass{
        fun getInterface(myClass1: MyClass1): MyInterface{
            return myClass1
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = MyMainClass().getInterface(MyClass1())
        a.getString()
    }
}