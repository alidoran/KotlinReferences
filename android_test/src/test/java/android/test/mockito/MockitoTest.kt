package android.test.mockito

import android.test.mockito.simple_mockito.MockLearnByMockito
import android.test.mockito.simple_mockito.UserMockInterface
import org.amshove.kluent.internal.assertEquals
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class MockitoTest {

    @Test
    fun userNameTest1() {
        val userMockInterfaceInterface = Mockito.mock(UserMockInterface::class.java)
        Mockito.`when`(userMockInterfaceInterface.userName()).thenReturn("Ali")
        val userName = userMockInterfaceInterface.userName()
        Assert.assertEquals(userName, "Ali")
    }

    @Test
    fun userNameTest2() {
        val mockLearnByMockito = MockLearnByMockito()
        val userMockInterface = Mockito.mock(UserMockInterface::class.java)
        Mockito.`when`(userMockInterface.userName()).thenReturn("Ali")
        val userName = mockLearnByMockito.callUserName(userMockInterface)
        Assert.assertEquals(userName, "Ali")
    }

    class OddEvenStringTest {
        @Test
        fun testOddEvenStringByMockingMethodInClass() {
            val mockedMockLearnByMockito = Mockito.spy(MockLearnByMockito()::class.java)
            Mockito.`when`(mockedMockLearnByMockito.randomNumber()).thenReturn(6)
            val oddEvenStringResult = mockedMockLearnByMockito.oddEvenStringMethodInClass()
            assertEquals("even", oddEvenStringResult)
        }
    }
}