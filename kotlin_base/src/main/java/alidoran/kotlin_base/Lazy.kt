package alidoran.kotlin_base

import android.util.Log

val lazyValue: String by lazy {
    Log.d("lazyValue", "Lazy value computed")
    "Hello"
}

class lateInit{
// After initial is the main different point between these two methods
    lateinit var lateInitVar: String
    private var nullabaleAllTime: String? = null

    fun initializing() {
        Log.d("Initialized?", this::lateInitVar.isInitialized.toString()) //false
        lateInitVar = "Value"
        Log.d("Initialized?", this::lateInitVar.isInitialized.toString()) //true
    }
}

