package alidoran.android.mvp.simple_mvp.view

import alidoran.android.databinding.ActivitySimpleMvpBinding
import alidoran.android.mvp.simple_mvp.presenter.ISimpleMvpPresenter
import alidoran.android.mvp.simple_mvp.presenter.SimpleMvpPresenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*

class SimpleMvpActivity : AppCompatActivity(), ISimpleMvpView {
    private lateinit var binding: ActivitySimpleMvpBinding
    private lateinit var iSimpleMvpPresenter: ISimpleMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()
        initListener()
    }

    private fun initListener() {
        binding.btnOk.setOnClickListener {
            iSimpleMvpPresenter.calculateNameCount(
                binding.txtName.text.toString()
            )
        }
    }

    private fun initPresenter() {
        iSimpleMvpPresenter = SimpleMvpPresenter(this)
    }

    override fun onClear() {
        binding.txtName.text.clear()
    }

    override fun onShowProgress() {
        binding.progressWait.visibility = VISIBLE
    }

    override fun onHideProgress() {
        binding.progressWait.visibility = INVISIBLE
    }

    override fun onUpdateNameCountResultInfo(name: String, nameCount: Int) {
        val result = "name = $name , Name Count = $nameCount"
        binding.txtResult.text = result
    }

}