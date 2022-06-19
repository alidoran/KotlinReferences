package android.mvp.presenter.controller


import android.mvp.model.UserInfoModel
import androidx.test.core.app.ActivityScenario.launch
import kotlinx.coroutines.*

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
