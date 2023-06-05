package alidoran.android.kotlin_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityKotlinFlowBinding
import alidoran.android.fake_endpoint.FakeEndpoint.fakeRepeatCallApi
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KotlinFlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKotlinFlowBinding
    private val vm by viewModels<KotlinFlowViewModel>()
    private lateinit var state: StateFlow<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnLaunchFlow.setOnClickListener { vm.simpleLaunchFlow() }
        btnFlowMap.setOnClickListener { vm.flowMap() }
        btnFlowFilter.setOnClickListener { vm.flowFilter() }
        btnFlowCatch.setOnClickListener { vm.flowCatch() }
        btnFlowAsLivedata.setOnClickListener { asLiveData() }
        btnFlowWithLifecycle.setOnClickListener { flowWithLifecycle() }
        btnRepeatLifecycleStarted.setOnClickListener { repeatOnLifecycleStarted() }
        btnRepeatLifecycleCreated.setOnClickListener { repeatOnLifecycleCreated() }
        btnStateFlow.setOnClickListener { stateFlow() }
    }

    private fun repeatOnLifecycleStarted() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fakeRepeatCallApi().collect{
                    Log.d("repeatOnLifecycleStarted", it.toString())
                }
            }
        }
    }

    private fun asLiveData() {
        val time = fakeRepeatCallApi().asLiveData()
        time.observe(this) {
            lifecycleScope.launch {
                Log.d("flowCatch", it.toString())
            }
        }
    }

    private fun flowWithLifecycle() {
        val timeFlow = fakeRepeatCallApi().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        vm.launchFlow("flowCatch", timeFlow)
    }

    private fun repeatOnLifecycleCreated() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                fakeRepeatCallApi().collect{
                    Log.d("repeatOnLifecycleCreated", it.toString())
                }
            }
        }
    }

    private fun stateFlow() {
        state = fakeRepeatCallApi().stateIn(
            initialValue = Log.d("", "Loading"),
            scope = lifecycleScope,
            started = SharingStarted.WhileSubscribed(5000)
        )
    }
}