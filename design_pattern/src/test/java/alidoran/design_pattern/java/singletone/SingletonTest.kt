package alidoran.design_pattern.java.singletone

import alidoran.design_pattern.java.singleton.SingletonJava
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.jupiter.api.Test


class SingletonTest {

    @Test
    fun `test singleton`(){
        val singletonInstance = SingletonJava.getInstance()
        val newInstance =
            SingletonJava()
        assertNotEquals(newInstance , SingletonJava.getInstance())
        assertEquals(singletonInstance, SingletonJava.getInstance())
    }

}