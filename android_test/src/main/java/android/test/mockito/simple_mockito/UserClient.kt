package android.test.mockito.simple_mockito

class UserClient {
    fun callUserName(userMockInterface: UserMockInterface): String{
        return userMockInterface.userName()
    }
}