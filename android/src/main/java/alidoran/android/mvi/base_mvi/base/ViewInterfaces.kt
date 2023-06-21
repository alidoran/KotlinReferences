package alidoran.android.mvi.base_mvi.base

interface ViewIntent
interface ViewAction
interface ViewState
interface IViewRenderer<STATE> {
    fun render(state: STATE)
}
