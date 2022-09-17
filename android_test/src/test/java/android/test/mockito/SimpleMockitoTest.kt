package android.test.mockito

import android.test.mockito.simple_mockito.UserClient
import android.test.mockito.simple_mockito.UserMockInterface
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class SimpleMockitoTest {

    @Test
    fun userNameTest1(){
        val userMockInterfaceInterface = Mockito.mock(UserMockInterface::class.java)
        Mockito.`when`(userMockInterfaceInterface.userName()).thenReturn("Ali")
        val userName = userMockInterfaceInterface.userName()
        Assert.assertEquals(userName,"Ali")
    }

    @Test
    fun userNameTest2(){
        val userClient = UserClient()
        val userMockInterface = Mockito.mock(UserMockInterface::class.java)
        Mockito.`when`(userMockInterface.userName()).thenReturn("Ali")
        val userName = userClient.callUserName(userMockInterface)
        Assert.assertEquals(userName,"Ali")
    }
}