package alidoran.android.mvi.base_mvi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState>
    : ViewModel(), IModel<STATE, INTENT> {

    protected val mState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return mState
        }

    override fun dispatchIntent(intent: INTENT) {

    }

}