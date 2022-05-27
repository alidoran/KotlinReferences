package ir.alidoran.teach_kotlin

import android.util.Log

class PropertiesLearn {

    enum class State { On, Off }

    var externalVar: Boolean = true


    //only run when method create and variable fill by 42
    private val withRun = run {
        println("Calculating the answer")
        42
    }

    //run when method calling
    private val withGetter: Int
        get() {
            println("Calculating the answer...")
            return 42
        }


    private var withSetter = false
        set(value) {
            println("$value changed in the withSetter")
            field = value
        }

    //external attribute using
    var state: State
        get() = if (externalVar) State.On else State.Off
        set(value) {
            externalVar = value == State.On
        }

    var internalVar: String = "abc"


    //extension properties
    private val String.lastIndex: Int
        get() = this.length - 1

    private val String.indices: IntRange
        get() = 0..lastIndex






    fun main() {
        println("$withRun $withRun $withGetter $withGetter")

        println(withSetter)
        withSetter = true
        println(withSetter)

        println(state)
        state = State.Off
        println(state)

        var string = "AliDoran"
        Log.d("lastIndex", string.lastIndex.toString())
        Log.d("indices" , string.indices.toString())
    }
}