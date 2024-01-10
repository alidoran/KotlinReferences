package android.test.fragment_test

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class TestFragmentViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel :TestFragmentViewModel
    @Before
    fun setupViewModel(){
        val application = mock(Application::class.java)
        viewModel = TestFragmentViewModel(application)
    }

    @Test
    fun get_response(){
//        viewModel.callFakeApi()
        assertThat(viewModel.returnThree(), `is`(3))
    }
}