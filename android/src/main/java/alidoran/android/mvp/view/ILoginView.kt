package alidoran.android.mvp.view

import android.content.Context

interface ILoginView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateLoginResultUserInfo(name: String, age: Int)
}