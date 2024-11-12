package alidoran.android.navigation_safe_args

import alidoran.android.R
import alidoran.android.databinding.FragmentNav02Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class NavFragment02() : Fragment() {

    private var _binding : FragmentNav02Binding? = null
    private val binding
    get() = _binding!!

    private val args: NavFragment02Args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNav02Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtWhole: TextView = view.findViewById(R.id.txt_whole_02)
        val receivedTxt = args.myArg
        txtWhole.text = receivedTxt.toString()

        val result = args.myArg + "Result"

//        setFragmentResult("requestKey", bundleOf("bundleKey" to result)) // Bundle back result
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle?.set("key", result) //live data back result
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}