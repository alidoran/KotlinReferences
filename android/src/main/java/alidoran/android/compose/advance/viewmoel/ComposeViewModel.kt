package alidoran.android.compose.advance.viewmoel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComposeViewModel: ViewModel() {
    private val _apiCall = MutableStateFlow("Default Text")
    val apiCall = _apiCall.asStateFlow()
    private val _multiTextRequest = MutableStateFlow("Default Text")
    val multiTextRequest = _multiTextRequest.asStateFlow()

    fun fakeCAllApi(){
        viewModelScope.launch {
            FakeEndpoint.fakeStringRequest()
                .collect{
                    _apiCall.value = it
                }
        }
    }

    fun callMultiString(){
        viewModelScope.launch {
            FakeEndpoint.fakeChecklistRepeatRequest(5)
                .collect{
                    _multiTextRequest.value = "$it"
                }
        }
    }
}