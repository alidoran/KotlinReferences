package android.test.juinit

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import android.test.junit5.UserJunit
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import org.junit.jupiter.params.support.AnnotationConsumer
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.stream.Stream

class ConvertTypeTest {

    @ParameterizedTest
    @ValueSource(strings = ["2019-02-14T15:00:00+02:00", "2019-01-01T12:30:00.999Z"])
    fun testArgumentConversion(dataTime: ZonedDateTime) {
        println("parsed zone is: $dataTime")
    }

    @ParameterizedTest
    @ValueSource(strings = ["2019-02-14", "2019-01-01"])
    fun testArgumentConversionTwo(localDate: LocalDate) {
        println("parsed zone is: $localDate")
    }

    // TODO: Comment it for parse error. Correct it later
//    @ParameterizedTest
//    @CsvFileSource(
//        resources = ["/test.csv"],
//        numLinesToSkip = 1, delimiter = '9'
//    )
//    fun testReadCsvFileModelInternalConvertor(@ConvertWith(User2ConverterTest::class) user: UserJunit) {
//    }

//    class User2ConverterTest : SimpleArgumentConverter() {
//        override fun convert(source: Any, targetType: Class<*>): Any {
//            return UserJunit.parse(source.toString())
//        }
//
//    }

    @ParameterizedTest
    @CsvFileSource(
        resources = ["/test.csv"],
        numLinesToSkip = 1,
        delimiter = '9'
    )
    fun testReadCsvFileModelParseConvertor(userJUnit: UserJunit) {
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = '9',
        value = [
            "Ali , Doran, 1",
            "Roya , Darvishi, 2",
            "Sattar, Khan, 3"]
    )
    fun testReadCsvFileModel(userJUnit: UserJunit) {
    }

    //region Annotation convertor test
    @ParameterizedTest
    @JsonSource(text = "[{\"code\": 1 , \"name\":  1362} , {\"code\": 2 , \"name\": 1363}]")
    fun testReadJsonModel(jsonNode: JsonNode) {
        val code = jsonNode["code"].asInt()
        val name = jsonNode["name"].asInt()
        Assertions.assertTrue(code<name)
    }

    @ArgumentsSource(JsonArgumentProvider::class)
    annotation class JsonSource(@Language("Json") val text: String)

    class JsonArgumentProvider : ArgumentsProvider,AnnotationConsumer<JsonSource> {
        private lateinit var text: String

        override fun accept(t: JsonSource) {
            this.text = t.text
        }

        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return ObjectMapper().readTree(text)
                .toList()
                .stream()
                .map { Arguments.of(it) }
        }
    }
    //endregion

}