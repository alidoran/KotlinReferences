package ir.alidoran.teach_kotlin

import android.content.ContentValues.TAG
import android.util.Log
import ir.alidoran.teach_kotlin.DataClassLearn.PersonDataClass
import ir.alidoran.teach_kotlin.DataClassLearn.dataClassMethods
import ir.alidoran.teach_kotlin.Sealed.exhaustive
import javax.security.auth.login.LoginException


object DataClassLearn {

    @JvmStatic
    fun main(args: Array<String>) {
        val personOne = PersonDataClass("Ali", 38)
        copy(personOne)
    }

    data class PersonDataClass(val name: String, val age: Int)

    class PersonClass(val name: String, val age: Int) {
        override fun toString(): String {
            return "Person(name=$name, age=$age)"
        }
    }

    private fun personList(): List<PersonDataClass> {
        var dataClassList = listOf(PersonDataClass("Alice", 29), PersonDataClass("Bob", 31))
        var classList = listOf(PersonClass("Alice", 29), PersonClass("Bob", 31))
        return dataClassList
    }

    private fun dataClassMethods() {
        var personDataClass = PersonDataClass("Ali", 37)
        personDataClass.copy(age = 38)
    }

    private fun referenceBetweenClassAndDataClass() {
        val personClass1 = PersonClass("Ali", 38)
        val personClass2 = PersonClass("Ali", 38)
        val personDataClass1 = PersonDataClass("Ali", 38)
        val personDataClass2 = PersonDataClass("Ali", 38)

        personClass1 == personClass2 //false => equal only check reference in class
        personDataClass1 == personDataClass2 //true => Two equal check content in data class
        personDataClass1 === personDataClass2 //false => three equal check reference in data class
    }

    //region dataclass advantage
    private fun destructuring() {
        //region destructuring
        val personOne = PersonDataClass("Ali", 38)
        val (name: String, age: Int) = personOne
        println(name + age)
    }

    private fun copy(person: PersonDataClass): PersonDataClass {
        return person.copy()
    }

    private fun equalCheck(person: PersonDataClass) {
        val personCopy = copy(person)
        val personSameReference = person

        var copyCheckData = person == personCopy //true
        var copyCheckReference = person == personCopy //false

        var personSameDataCheck = person == personSameReference //true
        var personSameReferenceCheck = person === personSameReference // true
    }
    //endregion
}

class ConstructorLearn() {
    class OldFormOfConstructor {
        fun oldFormOfConstructor(inputNumber: Int) {}
    }

    class OldFormOfConstructorDefinedVariable {
        var inputNumber: Int? = null;

        fun oldFormOfConstructorDefinedVariable(inputNumber: Int) {
            this.inputNumber = inputNumber
        }
    }

    private class NewFormOfConstructor(inputNumber: Int) {}

    private class NewFormOfConstructorDefined(var inputNumber: Int) {}

    private class VisibilityOfConstructor private constructor(var inputNumber: Int) {}

    private class TwoConstructor(inputNumber: Int, inputText: String) {
        constructor(inputNumber: String) : this(0, inputNumber) {}
    }
}

//You can used sealed to limit type to subclasses

object  Sealed {
    //region sealedInterface
    //create nonSealed for show differences
    interface InterfaceWithoutSealed {}
    class FirstClass : InterfaceWithoutSealed
    class SecondClass : InterfaceWithoutSealed
    fun interfaceWithoutSealedUsage(interfaceWithoutSealed: InterfaceWithoutSealed): Int =
        //1-doesn't have autocomplete in when
        when (interfaceWithoutSealed) {
            is FirstClass -> 1
            is SecondClass -> 2
            //2-force to use else
            else -> 0
        }

    sealed interface InterfaceSealed {}
    class ThirdClass : InterfaceSealed
    class FourthClass : InterfaceSealed

    fun interfaceSealedUsage(interfaceSealed: InterfaceSealed): Int =
        when (interfaceSealed) {
            is FourthClass -> TODO()
            is ThirdClass -> TODO()
        }
    //endregion
    //region sealedClass
    sealed class Result<out T : Any> {
        data class Success<out T : Any>(val data: T) : Result<T>()
        sealed class Error(val exceptions: Exceptions) : Result<Nothing>(){
            class KnownError(exceptions: Exceptions):Error(exceptions){}
            class UnknownError(exceptions: Exceptions):Error(exceptions){}
        }
        object InProgress : Result<Nothing>()
    }

    //In this case of sealed benefit all possible form of result are complete by Alt&Enter on when
    private fun handleResult(result: Result<Int>) {
        val error= when (result) {
            is Result.Error.KnownError -> TODO()
            is Result.Error.UnknownError -> TODO()
            Result.InProgress -> TODO()
            is Result.Success -> TODO()
        }.exhaustive
    }

    val <T> T.exhaustive: T
        get() = this
    //endregion

    private fun createResult(){
        val mResult = Result.Error.KnownError(Exceptions())
        handleResult(mResult)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        createResult()
    }
}

class InnerModifier() {
    class A {
        class B {
            class C {
                var b = B()

                inner class D {
                    var c = this@C
                }
            }
        }
    }

    fun callInnerClass() {
        val cClass: A.B.C
    }
}

class Delegation() {

    interface FirstInterface {
        fun getName(nameCode: Int): String
    }

    interface SecondInterface {
        fun getCode(name: String): Int
    }

    class GetCodeName(
        val firstInterface:
        FirstInterface, val secondInterface: SecondInterface
    ) : FirstInterface, SecondInterface {
        override fun getName(nameCode: Int): String {
            TODO("Not yet implemented")
        }

        override fun getCode(name: String): Int {
            TODO("Not yet implemented")
        }

    }

    //Delegation
    class GetCodeNameTwo(
        private val firstInterface: FirstInterface,
        private val secondInterface: SecondInterface
    ) :
        FirstInterface by firstInterface, SecondInterface by secondInterface {}

    fun use(getCodeNameTwo: GetCodeNameTwo) {
        getCodeNameTwo.getName(5)
    }

    //Dynamic Class
    class DynamicClass<T, G>(val t: Class<T>, val g: G) {
        fun dynamicMethod() {
            val declaredFieldList = t.declaredFields
            val c = g is Int
        }
    }
}

