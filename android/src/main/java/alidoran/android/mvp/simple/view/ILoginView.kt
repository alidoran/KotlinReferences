package alidoran.android.mvp.simple.view

interface ILoginView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateLoginResultUserInfo(name: String, age: Int)
}