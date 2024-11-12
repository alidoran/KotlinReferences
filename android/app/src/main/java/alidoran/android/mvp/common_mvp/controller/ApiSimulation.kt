package alidoran.android.mvp.common_mvp.controller

import alidoran.android.mvp.common_mvp.model.UserInfoModel
import kotlinx.coroutines.delay

object ApiSimulation {
    interface LoginControllerDelegate {
        suspend fun onSuccess(response: UserInfoModel)
        suspend fun onFailure()
    }

    suspend fun requestLogin(name: String, delegate: LoginControllerDelegate) {
            val userInfoModel = UserInfoModel(name, name.length)
            delay(3000)
            delegate.onSuccess(userInfoModel)
    }
}
