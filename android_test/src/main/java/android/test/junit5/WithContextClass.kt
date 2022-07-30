package android.test.junit5

import android.content.Context

class WithContextClass {

    fun stringCompare(context: Context, resId: Int, string: String):Boolean{
        return context.getString(resId) == string
    }
}