package android.test.coroutine

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.internal.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesLearnTest {
    @Test
    fun simpleCoroutineTest() = runTest{
        val result = CoroutinesLearn().simpleCoroutine()
        assertEquals("Simple Coroutine", result)
    }

    @Test //wrong test unless the test is passed. It consume real delay time.
    fun coroutineDispatcherTest() = runTest{
        val result = CoroutinesLearn().coroutineDispatcher()
        assertEquals("Simple Coroutine", result)
    }

    @Test
    fun callSuspendCoroutineTest() = runTest {
        val coroutinesLearn = CoroutinesLearn()
        launch { coroutinesLearn.callSuspendCoroutine() }
        advanceUntilIdle() // Yields to perform the registrations

        Assertions.assertEquals("This is the fake API result", coroutinesLearn.result.value)
    }
}