package doran.ali.juinit

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.*
import javax.crypto.Mac

class ExecutionConditionTest {

    @Disabled
    @Test
    fun testDisable(){
        println("disableTest")
    }

    @DisabledOnOs(OS.MAC)
    @Test
    fun testDisabledOnOsMac(){
        println("disabled On Os Mac Test")
    }

    @DisabledOnOs(OS.WINDOWS)
    @Test
    fun testDisabledOnOsWindows(){
        println("disabled On Os Windows Test")
    }

    @EnabledOnOs(OS.MAC)
    @Test
    fun testEnabledOnOsMac(){
        println("Enabled On Os Mac Test")
    }

    @DisabledOnJre(JRE.JAVA_8)
    @Test
    fun testDisabledOnSpecificJavaVersion(){
        println("Disabled On Specific Java Version")
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    fun testEnabledOnSpecificJavaVersion(){
        println("Enabled On Specific Java Version")
    }

    @DisabledIfEnvironmentVariable(named= "AliDoranEnvironmentVariable", matches = "true")
    @Test
    fun testDisabledIfEnvironmentVariable(){
        println("Disable If Environment Variable")
    }

    @EnabledIfEnvironmentVariable(named= "AliDoranEnvironmentVariable", matches = "true")
    @Test
    fun testEnabledIfEnvironmentVariable(){
        println("Enabled If Environment Variable")
    }



}