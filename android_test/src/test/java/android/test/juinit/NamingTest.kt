package android.test.juinit

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("This is a display name in class level")
class NamingTest {

    @Test
    fun testKluent() {
        val a=1840
        a.shouldBeEqualTo(1840)
        a.`should be equal to`(1840)
        a`should be equal to` 1840
    }

    @DisplayName("This is a different name for show in stack")
    @Test
    fun testDisplayName(){}



}