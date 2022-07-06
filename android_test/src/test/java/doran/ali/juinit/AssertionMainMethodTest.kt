package doran.ali.juinit

import doran.ali.junit5.AssertionMainMethod
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.NumberFormatException

class testAssertionMainMethod {

    @Test
    fun testCheckExceptionType() {
        // Be careful about inherits
        assertThrows<NumberFormatException> { AssertionMainMethod().isTrue(2) }
    }

    @Test
    fun testCheckNotException() {
        // Be careful about inherits
        assertDoesNotThrow { AssertionMainMethod().isTrue(1) }
    }

    @Test
    fun testAssertAll() {
        assertAll("a",
            { assertEquals("HelloWorld", "Hello" + "World") },
            { assertThrows<NumberFormatException> { AssertionMainMethod().isTrue(2) }},
            {assertDoesNotThrow { AssertionMainMethod().isTrue(1) }},
            { assertTrue(AssertionMainMethod().isTrue(1))},
            { assertFalse(AssertionMainMethod().isTrue(0))},
        )
    }
}