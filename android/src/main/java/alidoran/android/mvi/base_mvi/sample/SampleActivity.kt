package alidoran.android.mvi.base_mvi.sample

import android.os.Bundle
import alidoran.android.R
import alidoran.android.mvi.base_mvi.base.BaseActivity

class SampleActivity : BaseActivity<SampleIntent, SampleAction, SampleState, SampleViewModel>(SampleViewModel::class.java) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }
}