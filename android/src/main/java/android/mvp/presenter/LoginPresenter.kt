package android.mvp.presenter

import android.mvp.model.UserInfoModel
import android.mvp.presenter.controller.LoginController
import android.mvp.view.ILoginView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class LoginPresenter(var iLoginView: ILoginView) : ILoginPresenter {
    override fun clear() {
        iLoginView.onClear()
    }

    override fun showProgress() {
        iLoginView.onShowProgress()
    }

    override fun hideProgress() {
        iLoginView.onHideProgress()
    }

    override fun login(name: String, password: String) {
        showProgress()
        CoroutineScope(IO).launch {
            LoginController.requestLogin(
                name,
                password,
                object : LoginController.LoginControllerDelegate {
                    override suspend fun onSuccess(response: UserInfoModel) {
                        withContext(Main) {
                            iLoginView.onUpdateLoginResultUserInfo(response.name, response.age)
                            hideProgress()
                        }
                    }

                    override suspend fun onFailure() {
                        println("Failure")
                    }
                })
        }
    }
}