package ir.alidoran.teach_kotlin

import android.util.Log

enum class Color(val r: Int, val g: Int, val b: Int) {
    Blue(255, 0, 0),
    Orange(255, 165, 0),
    Red(255, 0, 0);

    fun rgb() = (r * 256 + g) * 256 + b
}


class EnumLearn {
    fun print() {
        Log.d("ColorEnumBlue", Color.Blue.toString())
        Log.d("ColorEnumBlueMethod", Color.Blue.rgb().toString())
        Log.d("ColorEnumOrangeMethod", Color.Orange.rgb().toString())
        Log.d("ColorEnumRedMethod", Color.Red.rgb().toString())
    }
}