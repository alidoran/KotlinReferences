package android.mvp.view

import android.content.Context
import com.example.android.databinding.ActivityMvpBinding
import android.mvp.presenter.ILoginPresenter
import android.mvp.presenter.LoginPresenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.core.view.isVisible

class MvpActivity : AppCompatActivity(), ILoginView {
    lateinit var binding: ActivityMvpBinding
    lateinit var iLoginPresenter: ILoginPresenter

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
                binding.txtId.text.toString(),
                binding.txtPass.text.toString()
            )
        }
    }

    private fun initPresenter() {
        iLoginPresenter = LoginPresenter(this)
    }

    override fun onClear() {
        binding.txtId.text.clear()
        binding.txtPass.text.clear()
    }

    override fun onShowProgress() {
        binding.progressWait.visibility = VISIBLE
    }

    override fun onHideProgress() {
        binding.progressWait.visibility = INVISIBLE
    }

    override fun onUpdateLoginResultUserInfo(name: String, age: Int) {
        binding.txtResult.text = "name = $name , age = $age"
    }

}