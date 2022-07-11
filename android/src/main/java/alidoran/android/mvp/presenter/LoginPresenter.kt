package alidoran.android.mvp.presenter

import alidoran.android.mvp.model.UserInfoModel
import alidoran.android.mvp.presenter.controller.LoginController
import alidoran.android.mvp.view.ILoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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