package android.test

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CallFunctionTest {
    @Before
    fun setup() {
    }

    @Test
    fun `is odd test`() {
        val callFunction = Mockito.spy(CallFunction::class.java)
        callFunction.isOdd(4)
        Mockito.verify(callFunction, Mockito.times(1)).oddString()
    }
}