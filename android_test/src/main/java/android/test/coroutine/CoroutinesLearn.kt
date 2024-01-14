package android.test.coroutine

import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class CoroutinesLearn {

    private val _result= MutableStateFlow("")
    val result : StateFlow<String> get() = _result


    suspend fun simpleCoroutine(): String{
        delay(10000)
        return "Simple Coroutine"
    }

    suspend fun coroutineDispatcher(): String = withContext(Dispatchers.IO){
        delay(10000)
        "Simple Coroutine"
    }

    suspend fun callSuspendCoroutine(){
        FakeEndpoint.fakeStringLongDelayRequest().collect{
            _result.value = it
        }
    }
}