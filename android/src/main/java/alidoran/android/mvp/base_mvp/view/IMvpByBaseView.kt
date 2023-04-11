package alidoran.android.mvp.base_mvp.view

import alidoran.android.mvp.base_mvp.base.BaseMvpView

interface IMvpByBaseView: BaseMvpView {

    fun onCalcNameCount(name: String, nameCount: Int)
}