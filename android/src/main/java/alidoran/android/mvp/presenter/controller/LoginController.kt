package alidoran.android.mvp.presenter.controller


import alidoran.android.mvp.model.UserInfoModel
import kotlinx.coroutines.delay

object LoginController {
    interface LoginControllerDelegate {
        suspend fun onSuccess(response: UserInfoModel)
        suspend fun onFailure()
    }

    suspend fun requestLogin(name: String, password: String, delegate: LoginControllerDelegate) {
            val userInfoModel = UserInfoModel(name, 39)
            delay(3000)
            delegate.onSuccess(userInfoModel)
    }
}
