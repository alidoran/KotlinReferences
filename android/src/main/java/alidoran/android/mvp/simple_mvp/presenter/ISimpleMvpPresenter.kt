package alidoran.android.mvp.simple_mvp.presenter

interface ISimpleMvpPresenter {
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(name: String)
}