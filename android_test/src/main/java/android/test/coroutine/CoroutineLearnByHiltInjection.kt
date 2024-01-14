package android.test.coroutine

import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutineLearnByHiltInjection @Inject constructor(private val fakeEndpoint: FakeEndpoint){
    private val _result= MutableStateFlow("")
    val result : StateFlow<String> get() = _result

    fun callCoroutines(){
        CoroutineScope(Dispatchers.Default).launch {
            fakeEndpoint.fakeStringLongDelayRequest().collect{
                _result.value = it
            }
        }
    }
}