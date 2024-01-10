package android.test.fragment_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestFragmentJ4ViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: TestFragmentViewModel

    @Before
    fun setup() {
        viewModel = TestFragmentViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun get_response() {
        assertThat(viewModel.returnThree(), `is`(3))
    }
}