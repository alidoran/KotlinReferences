package alidoran.design_pattern.java.singletone

import alidoran.design_pattern.java.singleton.SingletonKotlin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class SingletonKotlinTest {

    @Test
    fun `test singleton kotlin`(){
        val singletonKotlinInstance = SingletonKotlin.getInstance()
        val singletonKotlinNew = SingletonKotlin()
        assertEquals(SingletonKotlin.getInstance(), singletonKotlinInstance)
        assertNotEquals(SingletonKotlin.getInstance(), singletonKotlinNew)
    }
}