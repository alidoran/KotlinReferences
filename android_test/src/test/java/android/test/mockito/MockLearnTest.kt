package android.test.mockito

import android.test.mockito.simple_mockito.AddMockObject
import android.test.mockito.simple_mockito.MockLearn
import android.test.mockito.simple_mockito.MockLearn.Companion.staticObjectSample
import android.test.mockito.simple_mockito.UserMockInterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.spyk
import junit.framework.TestCase
import org.amshove.kluent.internal.assertEquals
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class MockLearnTest {
    @Test
    fun changeInterfaceResultTestMockito() {
        val userMockInterface = Mockito.mock(UserMockInterface::class.java)
        Mockito.`when`(userMockInterface.userName()).thenReturn("Ali")
        val userName = MockLearn().callByInterface(userMockInterface)
        Assert.assertEquals("Ali", userName)
    }

    @Test
    fun changeInterfaceResultTestMockk() {
        val userMockInterface = mockk<UserMockInterface>()
        every { userMockInterface.userName() } returns "Ali"
        val userName = MockLearn().callByInterface(userMockInterface)
        Assert.assertEquals("Ali", userName)
    }

    @Test
    fun changeMethodResultTestMockito() {
        val mockedMockLearn = Mockito.spy(MockLearn()::class.java)
        Mockito.`when`(mockedMockLearn.randomNumber()).thenReturn(6)
        val oddEvenStringResult = mockedMockLearn.oddEvenString()
        assertEquals("even", oddEvenStringResult)
    }

    @Test
    fun changeMethodResultTestMockk() {
        val mockedMockLearn = spyk(MockLearn())
        every { mockedMockLearn.randomNumber() } returns 6
        val oddEvenStringResult = mockedMockLearn.oddEvenString()
        assertEquals("even", oddEvenStringResult)
    }

    @Test
    fun mockObjectTestMockito() {
        Mockito.mockStatic(AddMockObject::class.java).use { _ ->
            Mockito.`when`(AddMockObject.add(1, 2)).thenReturn(5)
            val result = AddMockObject.add(1, 2)
            TestCase.assertEquals(5, result)
        }
    }

    @Test
    fun mockCompanionObjectTestMockk() {
        mockkObject(MockLearn.Companion)
        every { staticObjectSample() } returns "Ali"
        val result = staticObjectSample()
        assertEquals("Ali", result)
    }
}