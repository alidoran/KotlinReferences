package alidoran.android.mvi.base_mvi.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>>(private val modelClass: Class<VM>) :
    AppCompatActivity(), IViewRenderer<STATE> {

    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[modelClass]
        super.onCreate(savedInstanceState)

    }

    override fun render(state: STATE) {
        TODO("Not yet implemented")
    }

}