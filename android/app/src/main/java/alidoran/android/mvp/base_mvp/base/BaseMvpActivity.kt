package alidoran.android.mvp.base_mvp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseMvpActivity<T: BaseMvpPresenter<*>> : AppCompatActivity(), BaseMvpView {
    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectComponent()
    }

    abstract fun injectComponent()
}