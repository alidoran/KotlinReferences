package alidoran.android.mvi.basic_mvi.base

import alidoran.android.mvi.basic_mvi.viewstate.UserState

interface MviView<S: UserState> {
    fun render(state: S)
}