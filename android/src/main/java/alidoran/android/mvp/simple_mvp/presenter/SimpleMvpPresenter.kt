package alidoran.android.mvp.simple_mvp.presenter

import alidoran.android.mvp.common_mvp.model.UserInfoModel
import alidoran.android.mvp.common_mvp.controller.ApiSimulation
import alidoran.android.mvp.simple_mvp.view.ISimpleMvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimpleMvpPresenter(var ISimpleMvpView: ISimpleMvpView) : ISimpleMvpPresenter {
    override fun clear() {
        ISimpleMvpView.onClear()
    }

    override fun showProgress() {
        ISimpleMvpView.onShowProgress()
    }

    override fun hideProgress() {
        ISimpleMvpView.onHideProgress()
    }

    override fun calculateNameCount(name: String) {
        showProgress()
        CoroutineScope(IO).launch {
            ApiSimulation.requestLogin(
                name,
                object : ApiSimulation.LoginControllerDelegate {
                    override suspend fun onSuccess(response: UserInfoModel) {
                        withContext(Main) {
                            ISimpleMvpView.onUpdateNameCountResultInfo(response.name, response.nameCount)
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