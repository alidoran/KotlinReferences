package android.test.mockito

import android.test.mockito.simple_mockito.AddMockObject
import org.amshove.kluent.internal.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class MockObjectTest {
    @Test
    fun mockObjectAssertionTestMockito() {
        Mockito.mockStatic(AddMockObject::class.java).use { _ ->
            Mockito.`when`(AddMockObject.add(any(), any())).thenReturn(5)
            val result = AddMockObject.add(1, 2)
            assertEquals(5, result)
        }
    }

    @Test
    fun mockObjectAssertionTestMockito2() {
        Mockito.mockStatic(AddMockObject::class.java).use { m ->
            m.`when`<Int> { AddMockObject.add(1, 2) }.thenReturn(5)
            val result = AddMockObject.add(1, 2)
            assertEquals(5, result)
        }
    }
}