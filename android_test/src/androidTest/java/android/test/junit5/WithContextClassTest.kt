package android.test.junit5

import android.content.Context
import android.test.R
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertAll

class WithContextClassTest{
    @Test
    fun stringResourceIsEqualString(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val resultTrue = WithContextClass().stringCompare(context, R.string.amount, "Amount")
        val resultFalse = WithContextClass().stringCompare(context, R.string.amount, "amount")
        assertAll(
            {assertTrue(resultTrue)},
            {assertFalse(resultFalse)}
        )
    }
}