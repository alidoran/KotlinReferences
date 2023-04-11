package alidoran.android.mvp.simple_mvp.view

interface ISimpleMvpView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateLoginResultUserInfo(name: String, age: Int)
}