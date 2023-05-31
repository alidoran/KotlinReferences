package alidoran.android.mvi.basic_mvi

import alidoran.android.mvi.basic_mvi.intent.MviIntent
import alidoran.android.mvi.basic_mvi.viewstate.UserState
import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface MviModel<S: UserState, I: MviIntent> {
    val intents: Channel<I>
    val state: LiveData<S>
}