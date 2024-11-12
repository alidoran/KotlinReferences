package alidoran.android.add_dynamic_view

import alidoran.android.databinding.ActivityDynamicViewBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class DynamicViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDynamicViewBinding
    private val liveShowWait =MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var waitId = 0
        initialWait()

        binding.btnAddView.setOnClickListener {
            waitId = AddViewNonActivity(binding.root).addCustomWait()
        }

        binding.btnRemoveView.setOnClickListener {
            AddViewNonActivity(binding.root).removeCustomWait(waitId)
        }

        binding.btnLiveAddView.setOnClickListener {
            liveShowWait.postValue(true)
        }

        binding.btnLiveRemoveView.setOnClickListener {
            liveShowWait.postValue(false)
        }
    }

    private fun initialWait() =
        AddViewNonActivity(binding.root).addLiveCustomWait(this as LifecycleOwner, liveShowWait)
}