package alidoran.alidoran.thirdpartylibrarydependencies

import alidoran.third_party.apis.rest.retro_dagger.WeatherService3
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RetrofitTest {

    @Test
    fun interfaceTest() {
        runBlocking {
            val weatherMockitoInterface = Mockito.mock(WeatherService3::class.java)
            Mockito.`when`(
                weatherMockitoInterface.getWeatherApi3(q = "Tehran").body()!!.location.name)
                .thenReturn("Tehran")
            val city = weatherMockitoInterface.getWeatherApi3(q = "Tehran").body()!!.location.name

        }

    }
}


//private class FakeGeneratorWithoutMock() {
//}

//    override suspend fun getWeatherApi3(
//        key: String,
//        q: String,
//        aqi: String
//    ): Response<WeatherModel> {
        //Here we need to implement lot of attributes for creating WeatherModel
        //Here Mock is the main key
//            WeatherModel(
//                location = Location(name = "Tehran"),
//                current = Current(temp_c = 30)
//            )
//        }
//    }
//    }