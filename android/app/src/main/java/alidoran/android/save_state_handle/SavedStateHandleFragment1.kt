package alidoran.android.save_state_handle

import alidoran.android.databinding.FragmentSavedStateHandle1Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class SavedStateHandleFragment1 : Fragment() {

    private var _binding: FragmentSavedStateHandle1Binding? = null
    private val binding
        get() = _binding!!


    private val vm by viewModels<SavedStateHandleFragment1ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedStateHandle1Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noSaveState()
        viewModelSaveState()
        savedStateHandleCounter()
    }


    private fun noSaveState() {

        var counter = 0
        lifecycleScope.launch {
            while (true) {
                binding.txtNoSaveStateCounter.text = "No save state=$counter"
                counter++
                delay(TimeUnit.SECONDS.toMillis(1))
            }
        }
    }

    private fun viewModelSaveState() {
        // For testing this method only rotate your phone while countering
        vm.startVmCounter()
        vm.counterViewModelLiveData.observe(viewLifecycleOwner) {
            binding.txtViewmodelSaveStateCounter.text = it.toString()
        }
    }

    private fun savedStateHandleCounter() {
        // For testing this method go to Emulator-> Setting -> Developer option->
        //Background process limit-> No background processes
        vm.startSavedStateHandleCounter()
        vm.countSavedStateHandle.observe(viewLifecycleOwner) {
            binding.txtSavesStateBundleCounter.text = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}