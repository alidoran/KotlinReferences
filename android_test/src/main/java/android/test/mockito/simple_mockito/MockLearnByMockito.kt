package android.test.mockito.simple_mockito

import java.util.Random

class MockLearnByMockito {
    fun callUserName(userMockInterface: UserMockInterface): String {
        return userMockInterface.userName()
    }

    fun randomNumber(): Int {
        return Random().nextInt()
    }

    fun oddEvenStringMethodInClass(): String {
        val number = randomNumber()
        return if (number % 2 == 0) "even" else "odd"
    }
}