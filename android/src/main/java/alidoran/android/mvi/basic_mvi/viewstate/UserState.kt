package alidoran.android.mvi.basic_mvi.viewstate

import alidoran.android.mvi.basic_mvi.model.UserMviModel

sealed interface UserState {
    data class CallApi(val list: List<UserMviModel>) : UserState
    data class CallFlowApi(val list: List<UserMviModel>) : UserState
    object IsIdle: UserState
    object ShowLoading: UserState
    object HideLoading: UserState
    object ErrorMessage: UserState
}