package android.test.mockito.simple_mockito

import android.util.Log
import java.util.Random

class MockLearn {

    companion object {
        fun staticObjectSample() = if (Random().nextInt() % 2 == 0) "even" else "odd"
    }

    fun callByInterface(userMockInterface: UserMockInterface) = userMockInterface.userName()

    fun randomNumber(): Int = Random().nextInt()

    fun oddEvenString(): String = if (randomNumber() % 2 == 0) "even" else "odd"

    fun randomNumberBounded(bound: Int): Int = Random().nextInt(bound)
}

object MyObject {
    fun secondMethod(id: Int) {
        Log.d("TAG", "secondMethod: $id")
    }
}

fun firstMethod(id: Int) {
    MyObject.secondMethod(id)
}
