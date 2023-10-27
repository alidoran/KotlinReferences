package alidoran.android.mvi.basic_mvi.viewmodel

import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import alidoran.android.mvi.basic_mvi.intent.MviIntent
import alidoran.android.mvi.basic_mvi.MviModel
import alidoran.android.mvi.basic_mvi.viewstate.UserState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MviVm : ViewModel(), MviModel<UserState, MviIntent> {
    override val intents: Channel<MviIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<UserState>().apply { value = UserState.IsIdle }
    override val state: LiveData<UserState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun callApi() {
        _state.value = UserState.ShowLoading
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(UserState.CallApi(FakeEndpoint.fakeMviModelListRequest()))
        }
    }

    private fun callFlowApi() {
        _state.value = UserState.ShowLoading
        viewModelScope.launch(Dispatchers.IO) {
            FakeEndpoint.fakeMviModelListRequestFlow().collect {
                _state.postValue(UserState.CallFlowApi(it))
            }
        }
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { mviIntent ->
                when (mviIntent) {
                    MviIntent.FetchUserMvi -> callApi()
                    MviIntent.FetchUserMviFlow -> callFlowApi()
                    else -> {_state.postValue(UserState.HideLoading)}
                }
            }
        }
    }
}