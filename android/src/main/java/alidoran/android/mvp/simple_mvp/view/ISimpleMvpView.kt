package alidoran.android.mvp.simple_mvp.view

interface ISimpleMvpView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateNameCountResultInfo(name: String, nameCount: Int)
}