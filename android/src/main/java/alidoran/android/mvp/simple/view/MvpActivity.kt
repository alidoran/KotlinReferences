package alidoran.android.mvp.simple.view

import alidoran.android.databinding.ActivityMvpBinding
import alidoran.android.mvp.simple.presenter.ILoginPresenter
import alidoran.android.mvp.simple.presenter.LoginPresenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*

class MvpActivity : AppCompatActivity(), ILoginView {
    private lateinit var binding: ActivityMvpBinding
    private lateinit var iLoginPresenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()
        initListener()
    }

    private fun initListener() {
        binding.btnOk.setOnClickListener {
            iLoginPresenter.login(
                binding.txtName.text.toString()
            )
        }
    }

    private fun initPresenter() {
        iLoginPresenter = LoginPresenter(this)
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

    override fun onUpdateLoginResultUserInfo(name: String, age: Int) {
        val result = "name = $name , Name Count = $age"
        binding.txtResult.text = result
    }

}