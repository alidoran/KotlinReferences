package android.test.junit5

import android.content.Context
import android.test.R
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertAll

class WithContextClassTest{
    @Test
    fun stringResourceIsEqualString(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val context2 = ApplicationProvider.getApplicationContext<Context>()
        val resultTrue = WithContextClass().stringCompare(context, R.string.amount, "Amount")
        val resultFalse = WithContextClass().stringCompare(context2, R.string.amount, "amount")
        assertAll(
            {assertTrue(resultTrue)},
            {assertFalse(resultFalse)}
        )
    }
}