package android.test.mockito

import android.test.mockito.simple_mockito.First
import android.test.mockito.simple_mockito.Second
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TwoClassTest {
    @Test
    fun testMethodFirst2() {
        Mockito.mockConstruction(Second::class.java) { mock, _ ->
            doNothing().whenever(mock).methodSecond1()
        }.use { theMock ->
            First().methodFirst1()
            verify(theMock.constructed().first()).methodSecond1()
        }
    }

    @Test
    fun anotherTestMethodFirst2() {
        Mockito.mockConstruction(Second::class.java) { mock, _ ->
            doNothing().whenever(mock).methodSecond2()
        }.use { theMock ->
            First().methodFirst2()
            verify(theMock.constructed().first()).methodSecond2()
        }
    }
}