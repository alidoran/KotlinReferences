package alidoran.android.mvp.simple.presenter

interface ILoginPresenter {
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(name: String)
}