package ir.alidoran.teach_kotlin

import android.content.ContentValues.TAG
import android.util.Log
import ir.alidoran.teach_kotlin.CompanionLearn.CompanionExtension.fromJSON
import ir.alidoran.teach_kotlin.CompanionLearn.CompanionStatic.Creator.createNewInstance

class ObjectLearn {

    //region JvmStatic and java access
    object ObjectWithoutAnnotation {
        var a = 2
    }

    object ObjectWithAnnotation {
        @JvmStatic
        var b = 2
    }
    //endregion

    // Object in Kotlin is like private SingleTone in Java
    object SingletonKotlin {
        fun objectMethod() {}
    }

    fun main() {
        SingletonKotlin.objectMethod()
    }

    //Companion is like static method
    class Person(val firstName: String, val lastName: String) {
        companion object {
            fun getName(nameId: Int): String {
                return "Companion Inner Method"
            }
        }
    }

    fun Person.Companion.getName(nameId: Int): String {
        return "Companion Extension"
    }

    val a = Person.getName(21)
    val b = Person.getName(21)
}

object CompanionLearn {
    //Look like static method in java
    @JvmStatic
    fun main(args: Array<String>) {
        CompanionStatic.Al
        createNewInstance(CompanionStatic.Al).innerMethod()
        CompanionStatic.Al.create().innerMethod()
        CompanionExtension.Person.fromJSON()
    }

    object CompanionStatic {
        object Creator {
            fun <T> createNewInstance(factory: Factory<T>): T {
                return factory.create()
            }
        }


        interface Factory<T> {
            fun create(): T
        }

        class Al private constructor() {
            companion object : Factory<Al> {
                override fun create() = Al()
            }

            fun innerMethod() {}
        }
    }

    object CompanionExtension {
        class Person(val firstName: String, val lastName: String) {
            companion object {}
        }

        fun Person.Companion.fromJSON() {
            Log.d("fromJSON", "fromJSON")
        }
    }


}

class LearnObjectExpression {
    private fun sampleInputInterface(interfaceTwoFun: InterfaceLearn.InterfaceTwoFun) {
        Log.d(TAG, interfaceTwoFun.firstAttribute(1))
        Log.d(TAG, interfaceTwoFun.secondAttribute(2))
    }

    fun callSampleInputInterface() {
        sampleInputInterface(object : InterfaceLearn.InterfaceTwoFun {
            override fun firstAttribute(intOne: Int): String {
                return (intOne == 1).toString()
            }

            override fun secondAttribute(intTwo: Int): String {
                return (intTwo == 1).toString()
            }

        })
    }
}