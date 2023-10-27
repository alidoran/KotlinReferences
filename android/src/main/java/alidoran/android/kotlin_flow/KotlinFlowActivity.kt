package alidoran.android.kotlin_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityKotlinFlowBinding
import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeCallOneToThreeApi
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeStringListRequest
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.commonlibrary.fake_endpoint.FakeEndpoint.fakeMviModelListRequest
import com.example.commonlibrary.fake_endpoint.NameModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class KotlinFlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKotlinFlowBinding
    private val vm by viewModels<KotlinFlowViewModel>()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    @FlowPreview
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
        btnSharedFlow.setOnClickListener { sharedFlow() }
        btnMap.setOnClickListener { mapLearn() }
        btnCallPriority.setOnClickListener { callPriority() }
        btnCallInnerApi.setOnClickListener { callInnerApi() }
        btnResponseAfterDestroy.setOnClickListener { apiResponseAfterOnDestroyed() }
        btnFirst.setOnClickListener { lifecycleScope.launch { first() } }
        btnFirstCatch.setOnClickListener { lifecycleScope.launch { firstCatch() } }
    }

    private fun repeatOnLifecycleStarted() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fakeCallOneToThreeApi().collect {
                    Log.d("repeatOnLifecycleStarted", it.toString())
                }
            }
        }
    }

    private fun asLiveData() {
        val time = fakeCallOneToThreeApi().asLiveData()
        time.observe(this) {
            lifecycleScope.launch {
                Log.d("flowCatch", it.toString())
            }
        }
    }

    private fun flowWithLifecycle() {
        val timeFlow = fakeCallOneToThreeApi().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        vm.launchFlow("flowCatch", timeFlow)
    }

    private fun repeatOnLifecycleCreated() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                fakeCallOneToThreeApi().collect {
                    Log.d("repeatOnLifecycleCreated", it.toString())
                }
            }
        }
    }

    private fun stateFlow() {
        lifecycleScope.launch {
            fakeCallOneToThreeApi().collect {
                Log.d("stateFlow", "stateFlow: ${it.toString()} ")
            }
        }
    }

    private fun sharedFlow() {
        val state: SharedFlow<Int> = fakeCallOneToThreeApi().stateIn(
            initialValue = Log.d("", "Loading"),
            scope = lifecycleScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

        lifecycleScope.launch {
            state.collect {
                Log.d("stateFlow", "stateFlow: ${it.toString()} ")
            }
        }
    }

    @FlowPreview
    private fun mapLearn() {
        lifecycleScope.launch {
            vm.mapLearn().collect {
                Log.d("AliDoranFlow", it.mString)
            }

            vm.mapLearn().map {
                FlowModelFull(
                    true,
                    it.mString
                )
            }.collect {
                Log.d("AliDoranFlow", "${it.mString} ${it.mBoolean}")
            }

            vm.mapLearn().flatMapMerge {
                flow {
                    emit(
                        FlowModelFull(
                            true,
                            it.mString
                        )
                    )
                }
            }.collect {
                Log.d("AliDoranFlow", "${it.mString} ${it.mBoolean}")
            }
        }
    }

    fun callPriority() {
        lifecycleScope.launch {
            FakeEndpoint.fakeStringRequest()
                .onEach { Log.d("callPriority", "onEach") }
                .onCompletion { Log.d("callPriority", "onCompletion") }
                .onStart { Log.d("callPriority", "onStart") }
                .collect { Log.d("callPriority", "collect") }
        }
    }

    private fun a() {
        lifecycleScope.launch {
            fakeStringListRequest().zip(fakeStringListRequest()) { i, s ->
            }
        }
    }


    private fun callInnerApi() {
        var secondApiOnEachResult = ArrayList<NameModel>()
        var secondApiFlatMapResult = ArrayList<NameModel>()
        lifecycleScope.launch {
            FakeEndpoint.fakeStringRequest()
                .onEach {
                    secondApiOnEachResult = fakeMviModelListRequest()
                }
                .map {
                    secondApiFlatMapResult = fakeMviModelListRequest()
                    it
                }.collect {}
            Log.d("TAG", "secondApiOnEachResult: ${secondApiOnEachResult[0].name}")
            Log.d("TAG", "secondApiFlatMapResult: ${secondApiFlatMapResult[0].name}")
        }
    }

    private fun apiResponseAfterOnDestroyed() {
        vm.longDelay()
        finish()
    }

    private suspend fun first() {
        val a = FakeEndpoint.fakeIntReapeatRequest().first()
        Log.d("single", "single: $a")
    }

    private suspend fun firstCatch() {
        val a = FakeEndpoint.fakeCatchRequest().first()
        Log.d("single", "single: $a")
    }
}