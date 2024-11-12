package alidoran.android.mvi.basic_mvi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityMviBinding
import alidoran.android.mvi.basic_mvi.intent.MviIntent
import alidoran.android.mvi.basic_mvi.base.MviView
import alidoran.android.mvi.basic_mvi.viewstate.UserState
import alidoran.android.mvi.basic_mvi.viewmodel.MviVm
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MviActivity : AppCompatActivity(), MviView<UserState> {

    private val mViewModel by viewModels<MviVm>()
    private lateinit var binding: ActivityMviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel.state.observe(this) {
            render(it)
        }

        lifecycleScope.launch {
            mViewModel.stateFlow.collect {
                render(it)
            }
        }

        binding.btnMvi.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.intents.send(MviIntent.FetchUserMvi)
            }
        }

        binding.btnMviFlow.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.intents.send(MviIntent.FetchUserMviFlow)
            }
        }

        binding.btnMviStateFlow.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.intents.send(MviIntent.FetchUserMviStateFlow)
            }
        }
    }

    override fun render(state: UserState) {
        when (state) {
            is UserState.CallApi -> {
                binding.progressMvi.isVisible = false
                Log.d("CallApi Response", "render: ${state.list.size}")
            }

            is UserState.CallFlowApi -> {
                binding.progressMvi.isVisible = false
                Log.d("CallFlowApi Response", "render: ${state.list.size}")
            }

            is UserState.CallStateFlowApi -> {
                binding.progressMvi.isVisible = false
                Log.d("CallStateFlowApi Response", "render: ${state.list.size}")
            }

            is UserState.ShowLoading ->
                binding.progressMvi.isVisible = true

            else -> {
                binding.progressMvi.isVisible = false
            }
        }
    }
}