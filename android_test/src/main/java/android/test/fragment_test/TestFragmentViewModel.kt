package android.test.fragment_test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import kotlinx.coroutines.launch

class TestFragmentViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _apiResponse = MutableLiveData<String>()
    val apiResponse: LiveData<String> = _apiResponse

    fun callFakeApi() {
        viewModelScope.launch {
            FakeEndpoint.fakeStringRequest().collect{
                _apiResponse.value = it
            }
        }
    }

    fun returnThree(): Int{
        return 3
    }
}