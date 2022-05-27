package alidoran.design_pattern.java.test

import java.util.ArrayList

class ContentModelTest {
    private val list = ArrayList<String>()


    fun push(string: String) {
        list.add(string)
    }

    fun pull(): String {
        val tmp = list.last()
        list.remove(list.last())
        return tmp
    }

    fun undo(){

    }
}