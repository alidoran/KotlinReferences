package alidoran.android.mvp.base_mvp.base

open class BaseMvpPresenter<T: BaseMvpView> {
    lateinit var mView: T

}