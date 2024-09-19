package alidoran.android.mvi.basic_mvi.viewstate

import com.example.commonlibrary.fake_endpoint.NameModel

sealed interface UserState {
    data class CallApi(val list: List<NameModel>) : UserState
    data class CallFlowApi(val list: List<NameModel>) : UserState
    data class CallStateFlowApi(val list: List<NameModel>) : UserState
    object IsIdle: UserState
    object ShowLoading: UserState
    object HideLoading: UserState
    object ErrorMessage: UserState
}