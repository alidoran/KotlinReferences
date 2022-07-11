package android.test.juinit

import org.junit.jupiter.api.*
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.api.parallel.ResourceAccessMode
import org.junit.jupiter.api.parallel.ResourceLock
import java.util.concurrent.TimeUnit

//1-add it in gradle
//testOptions {
//    unitTests.all {
//        systemProperty "junit.jupiter.execution.parallel.enabled", "true"
//    }
//}
//2-select/run configuration -> edit configuration -> Vm option
//add -> -Djunit.jupiter.execution.parallel.enabled=true
//3-add this in your code -> @Execution(ExecutionMode.CONCURRENT)
//or add in vm option for changing default ->
// -Djunit.jupiter.execution.parallel.mode.default=concurrent

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParallelTest {
    var temp :String = ""

    @Test
    fun testOne() {
        for (i in 1..9999999999) {
            temp="AliDoran"
        }
        Assertions.assertTrue(true)
    }

    @Test
    fun testTwo() {
        for (i in 1..10000) {
            temp="AliDoran"
        }
        Assertions.assertTrue(true)
    }

    @Test
    fun testThree() {
        for (i in 1..5555555555) {
            temp="AliDoran"
        }
        Assertions.assertTrue(true)
    }

    @TestFactory
    fun testParallelExecutionConfigurationStrategyThreadSleep() = LongRange(0,100)
            .map{num ->
                dynamicTest("#Test =  $num" ){
                    println("current thread is ${Thread.currentThread().name}")
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5))
                }
            }


    @TestFactory
    fun testParallelExecutionConfigurationStrategy() = LongRange(0,100)
        .map{num ->
            dynamicTest("#Test =  $num" ){
                println("current thread is ${Thread.currentThread().name}")
                for (i in 0..9999999999){
                    temp="AliDoran"
                }
            }
        }


    // add it in VM Option -> -Djunit.jupiter.execution.parallel.config.dynamic.factor=2
    @TestFactory
    fun testParallelExecutionConfigurationStrategyMultiPlyEachCore() = LongRange(0,100)
        .map{num ->
            dynamicTest("#Test =  $num" ){
                println("current thread is ${Thread.currentThread().name}")
                Thread.sleep(TimeUnit.SECONDS.toMillis(5))
            }
        }
}

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParallelTestLock {

    lateinit var string: String

    @BeforeEach
    fun testBeforeEach(){
        string = String()
    }

    @ResourceLock("System Properties", mode = ResourceAccessMode.READ_WRITE)
    @RepeatedTest(10)
    fun testParallelByLockProperties() {
            Thread().run{
                string += Thread.currentThread().name
                Assertions.assertEquals(string, Thread.currentThread().name)
            }
    }
}