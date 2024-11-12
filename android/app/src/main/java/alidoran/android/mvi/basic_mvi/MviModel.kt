package alidoran.android.mvi.basic_mvi

import alidoran.android.mvi.basic_mvi.intent.MviIntent
import alidoran.android.mvi.basic_mvi.viewstate.UserState
import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

interface MviModel<S: UserState, I: MviIntent> {
    val intents: Channel<I>
    val state: LiveData<S>
    val stateFlow: StateFlow<S>
}