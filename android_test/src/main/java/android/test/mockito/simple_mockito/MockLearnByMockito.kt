package android.test.mockito.simple_mockito

import java.util.Random

class MockLearnByMockito {

    private var myEnum = MockitoEnums.MockEnumSample.Even
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

    fun oddEvenStringMethodInClassEnum(): Int {
        myEnum = MockitoEnums.MockEnumSample.Odd
        val result =
            when (myEnum) {
                MockitoEnums.MockEnumSample.Even -> calByEnum(0)
                MockitoEnums.MockEnumSample.Odd -> calByEnum(1)
            }
        return result
    }

    fun calByEnum(it:Int): Int{
        return 5
    }
}