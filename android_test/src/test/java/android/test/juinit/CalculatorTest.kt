package android.test.juinit


import android.test.junit5.Calculator
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.lang.IllegalArgumentException

class CalculatorTest {
    private var calculator = Calculator()

    @Test
    fun testAddingTwoPositiveNum() {
        println("testAddingTwoPositiveNum")
        val result: Int = calculator.add(20, 30)
        assertEquals(50, result)
    }

    @Test
    fun testAddingTwoPositiveNumByKluentLibrary() {
        println("testAddingTwoPositiveNumByKluentLibrary")
        calculator.add(20,30).shouldBeEqualTo(50)
    }

    @Test
    fun testSubPositiveNum() {
        println("testSubPositiveNum")
        val result: Int = calculator.sub(30, 10)
        assertEquals(20, result)
    }

    @Nested
    inner class Divide {
        @Test
        fun testDividePositiveNumNonZero() {
            println("testDividePositiveNumNonZero")
            val result: Int = calculator.divide(30, 10)
            assertEquals(3, result)
        }

        @Test
        fun testDividePositiveNumToZero() {
            println("testDividePositiveNumZero")
            assertThrows(IllegalArgumentException::class.java) {
                calculator.divide(1, 0)
            }
        }

        @Test
        fun testDividePositiveNumToZeroByKluent() {
            println("testDividePositiveNumToZeroByKluent")
            invoking { calculator.divide(1, 0) }.shouldThrow(IllegalArgumentException::class)
        }
    }


}