package android.test.juinit

import android.test.junit5.UserJunit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource


internal class UserJunitJunitTest {

    @Test
    fun testChangeReputation() {
        val user = UserJunit(0, "Ali", "Doran")
        user.changeReputation(10)
        assertEquals(10, user.id)
    }

    @ParameterizedTest
    @CsvSource(
        "0 , '', 'Doran'",
        "0 , 'Ali', ''",
        "'1840' , '',''"
    )
    fun testIsFillParameters(id: Int, firstName: String, lastName: String) {
        assertThrows(IllegalArgumentException::class.java) {
            UserJunit(id, firstName, lastName).isFillParameters()
        }
    }


    //First method
    companion object {
        @JvmStatic
        @Suppress("unused")
        fun testFillParameterList() = listOf(
            Arguments.of("0", "","Doran"),
            Arguments.of("0", "Ali",""),
            Arguments.of("1840", "","")
        )

        @ParameterizedTest
        @MethodSource("fillParameterList")
        fun testIsFillParameters(id: Int, firstName: String, lastName: String) {
            assertThrows(IllegalArgumentException::class.java) {
                UserJunit(id, firstName, lastName).isFillParameters()
            }
        }

    }

    //Second method
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FillTest{

        @Suppress("unused")
        fun testFillParameterList() = listOf(
            Arguments.of("1840", "", "Doran"),
            Arguments.of("0", "Ali",""),
            Arguments.of("1840", "", "")
        )

        @ParameterizedTest
        @MethodSource("fillParameterList")
        fun testIsFillParameters(id: Int, firstName: String, lastName:String) {
            assertThrows(IllegalArgumentException::class.java) {
                UserJunit(id, firstName, lastName).isFillParameters()
            }
        }
    }
}