package alidoran.android.fragment

import alidoran.android.databinding.FragmentMyBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)
        // Inflate the layout for this fragment
        binding = FragmentMyBinding.inflate(layoutInflater)
//        return inflater.inflate(R.layout.fragment_my, container, false)
        return binding.root
    }
}