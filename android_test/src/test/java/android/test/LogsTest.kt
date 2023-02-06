package android.test

import org.junit.Before
import org.junit.Test

//@ExtendWith(OutputCaptureExtension::class)
class LogsTest {



    @Before
    fun setup() {
    }

    @Test
    fun `normal log test`() {
        Logs().normalLog(true)

    }
//
//    @AfterEach
//    fun after(output: CapturedOutput) {
//        assertThat(true, output.getOut().contains("ok"))
//    }
}