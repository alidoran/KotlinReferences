package doran.ali.juinit

import org.junit.jupiter.api.*


class InnerNestedClassTest {

    @BeforeEach
    fun testBeforeEachMainClass(){
        println("beforeEachMainClass")
    }

    @AfterEach
    fun testAfterEachMainClass(){
        println("afterEachMainClass")
    }


    @Test
    fun testMainClassFunction() {
        println("mainClassFunction")
    }

    @Nested
    inner class FirstInnerClassTest{
        @BeforeEach
        fun testBeforeEachMainClass(){
            println("beforeEachFirstInnerClass")
        }

        @AfterEach
        fun testAfterEachMainClass(){
            println("afterEachFirstInnerClass")
        }

        @Test
        fun testMainFirstInnerClassFunction(){
            println("FirstInnerClassFunction")
        }
    }

    @Nested
    inner class SecondInnerClassTest{
        @BeforeEach
        fun testBeforeEachSecondClass(){
            println("beforeEachSecondInnerClass")
        }

        @AfterEach
        fun testAfterEachSecondClass(){
            println("afterEachSecondInnerClass")
        }

        @Test
        fun testMainSecondInnerClassFunction(){
            println("SecondInnerClassFunction")
        }
    }
}