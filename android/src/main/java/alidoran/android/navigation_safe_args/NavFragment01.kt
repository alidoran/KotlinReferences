package alidoran.android.navigation_safe_args

import alidoran.android.databinding.FragmentNav01Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class NavFragment01 : Fragment() {

    private var _binding :FragmentNav01Binding? = null
    private val binding
    get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNav01Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region Bundle Back Result
        /*
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            if (requestKey == "requestKey")
                println(result)
        }*/
        //endregion
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextPage.setOnClickListener{
            val amount = binding.edtMyArgumentView.text.toString()
            val action = NavFragment01Directions.actionNavFragment01ToNavFragment02(amount)
            findNavController().navigate(action)
        }

        //region livedata back result
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("key")
            ?.observe(viewLifecycleOwner) {
            val result = it
        }
        //endregion
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}