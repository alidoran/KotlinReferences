package alidoran.android.mvp.base_mvp.presenter

import alidoran.android.mvp.base_mvp.base.BaseMvpPresenter
import alidoran.android.mvp.base_mvp.view.IMvpByBaseView
import alidoran.android.mvp.common_mvp.controller.ApiSimulation
import alidoran.android.mvp.common_mvp.model.UserInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MvpByBasePresenter @Inject constructor(): BaseMvpPresenter<IMvpByBaseView>() {
    fun calculateNameCount(name: String) {
//        showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            ApiSimulation.requestLogin(
                name,
                object : ApiSimulation.LoginControllerDelegate {
                    override suspend fun onSuccess(response: UserInfoModel) {
                        withContext(Dispatchers.Main) {
                            mView.onCalcNameCount(response.name, response.nameCount)
//                            hideProgress()
                        }
                    }

                    override suspend fun onFailure() {
                        println("Failure")
                    }
                })
        }
    }
}