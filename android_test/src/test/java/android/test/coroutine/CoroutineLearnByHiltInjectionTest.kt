package android.test.coroutine

import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class CoroutineLearnByHiltInjectionTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeEndpoint: FakeEndpoint

    @Test
    fun testCallCoroutines() = runBlockingTest {
        // Arrange
        val fakeString = "fake string"
        Mockito.`when`(fakeEndpoint.fakeStringLongDelayRequest()).thenReturn(flowOf(fakeString))
        val coroutineLearnByHiltInjection = CoroutineLearnByHiltInjection(fakeEndpoint)

        // Act
        coroutineLearnByHiltInjection.callCoroutines()

        // Assert
        Assertions.assertEquals(fakeString, coroutineLearnByHiltInjection.result.value)
    }
}