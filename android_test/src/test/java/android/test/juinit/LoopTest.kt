package android.test.juinit

import android.test.junit5.Calculator
import android.test.junit5.SampleEnumClass
import android.test.junit5.UserJunit
import org.amshove.kluent.internal.assertEquals
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*

class LoopTest {

    @Test
    fun testForLoop() {
        for (i in 1..10000) {
            assertAll(
                { Assertions.assertTrue(Calculator().multiplySix(i) % 3 == 0) },
                { Assertions.assertTrue(Calculator().multiplySix(i) % 2 == 0) }
            )
        }
    }

    @RepeatedTest(
        value = 20,
        name = "number of repetition is {currentRepetition}"
    ) //Show the value witch have problem
    fun testRepeated(repetitionInfo: RepetitionInfo) {
        val i = repetitionInfo.currentRepetition
        assertAll(
            { Assertions.assertTrue(Calculator().multiplySix(i) % 3 == 0) },
            { Assertions.assertTrue(Calculator().multiplySix(i) % 2 == 0) }
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 7])
    fun testIntegerParameterized(i: Int) {
        assertAll(
            { Assertions.assertTrue(Calculator().multiplySix(i) % 3 == 0) },
            { Assertions.assertTrue(Calculator().multiplySix(i) % 2 == 0) }
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["Ali", "Sattar"])
    fun testStringParameterized(name: String) {
        assertEquals(UserJunit(1, name,"").nameToLowercase(), name.lowercase())
    }

    companion object {
        @JvmStatic
        fun testMethodSourceProvider() = listOf(
            Arguments.of("AliDoran", 1362),
            Arguments.of("Roya", 1363)
        )
    }

    @ParameterizedTest
    @MethodSource("testMethodSourceProvider")
    fun testMethodSource(name: String, birthDay: Int) {
        Assertions.assertTrue(name.length > 2)
        Assertions.assertTrue(birthDay > 1300)
    }

    @ParameterizedTest
    @EnumSource(SampleEnumClass::class)
    fun testEnum(sampleEnumClass: SampleEnumClass) {
        val season = sampleEnumClass.toString()
        Assertions.assertTrue(
            season.startsWith("S")
                    || season.startsWith("A")
                    || season.startsWith("W")
        )
    }

    @ParameterizedTest
    @CsvSource( value = [
            "Ali , Doran",
            "Roya , Darvishi",
            "Sattar, Khan"])
    fun testCsvSource(firstName:String, lastName:String){
        assertAll(
            {Assertions.assertTrue(UserJunit(0,firstName,lastName).concatNameAndFamily()== firstName + lastName)},
            {Assertions.assertTrue(lastName.length>=4)}
        )
    }
}