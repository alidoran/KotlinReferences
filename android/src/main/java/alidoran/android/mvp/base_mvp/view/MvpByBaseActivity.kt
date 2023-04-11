package alidoran.android.mvp.base_mvp.view

import alidoran.android.databinding.ActivityMvpByBaseBinding
import alidoran.android.mvp.base_mvp.base.BaseMvpActivity
import alidoran.android.mvp.base_mvp.presenter.MvpByBasePresenter
import android.os.Bundle

class MvpByBaseActivity : BaseMvpActivity<MvpByBasePresenter>(), IMvpByBaseView {

    private lateinit var binding: ActivityMvpByBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpByBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener { calc() }
    }

    override fun injectComponent() {
        mPresenter.mView = this
    }

    private fun calc() {
        val name = binding.txtName.text.toString()
        mPresenter.calculateNameCount(name)
    }

    override fun onCalcNameCount(name: String, nameCount: Int) {
        val result = "name = $name , Name Count = $nameCount"
        binding.txtResult.text = result
    }
}