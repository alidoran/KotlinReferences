package android.test.juinit

import android.test.CodeNameModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_METHOD) //This is default value
// Default value can be change in gradle
class TestInstanceMode {
    @Test
    fun testFirst(){
        println("Test instance code is $this")
    }

    @Test
    fun testSecond(){
        println("Test instance code is $this")
    }

    @Test
    fun testModelReference(){
        val a= CodeNameModel("AliDoran", 1840)
        val b= CodeNameModel("AliDoran", 1840)

        Assertions.assertNotSame(a,b)
        Assertions.assertEquals(a,b)
//        Assertions.assertSame(a,b, "Message if assert failed")
    }
}