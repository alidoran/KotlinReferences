package alidoran.android.mvp.simple.presenter

import alidoran.android.mvp.simple.model.UserInfoModel
import alidoran.android.mvp.controller.ApiSimulation
import alidoran.android.mvp.simple.view.ILoginView
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

    override fun login(name: String) {
        showProgress()
        CoroutineScope(IO).launch {
            ApiSimulation.requestLogin(
                name,
                object : ApiSimulation.LoginControllerDelegate {
                    override suspend fun onSuccess(response: UserInfoModel) {
                        withContext(Main) {
                            iLoginView.onUpdateLoginResultUserInfo(response.name, response.nameCount)
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