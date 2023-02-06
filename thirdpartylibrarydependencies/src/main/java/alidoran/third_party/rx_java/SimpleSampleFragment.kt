package alidoran.third_party.rx_java

import alidoran.third_party.databinding.FragmentSimpleSampleBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SimpleSampleFragment : Fragment() {

    lateinit var binding: FragmentSimpleSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleSampleBinding.inflate(inflater,container,false)
        return binding.root
    }
}