package android.mvp.presenter

interface ILoginPresenter {
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(name: String, password: String)
}