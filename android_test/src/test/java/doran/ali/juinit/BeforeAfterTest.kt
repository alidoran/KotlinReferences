package doran.ali.juinit

import org.junit.jupiter.api.*

class BeforeAfterTest {

    //You can remove this companion if you identify instance in per_class form
    companion object{
        @JvmStatic
        @BeforeAll
        fun testBeforeAll(){
            println("BeforeAll")
        }

        @JvmStatic
        @AfterAll
        fun testAfterAll(){
            println("AfterAll")
        }
    }

    @BeforeEach
    fun testBeforeClassCompanion() {
        println("BeforeEach")
    }

    @AfterEach
    fun testAfterClassCompanion() {
        println("AfterEach")
    }

    @Test
    fun testFirst(){
        println("First Test")
    }

    @Test
    fun testSecond(){
        println("Second Test")
    }
}