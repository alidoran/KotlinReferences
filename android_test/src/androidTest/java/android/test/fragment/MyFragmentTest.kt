package android.test.fragment

import android.test.fragment_test.MyFragment
import android.test.R
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class MyFragmentTest: TestCase(){
    private lateinit var scenario:FragmentScenario<MyFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Test)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testAddingSpend(){
        onView(withId(R.id.edit_text_amount)).perform(typeText("Ali"))

    }
}