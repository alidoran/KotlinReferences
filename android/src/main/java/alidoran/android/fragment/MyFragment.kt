package alidoran.android.fragment

import alidoran.android.databinding.FragmentMyBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MyFragment : Fragment() {

    private lateinit var binding: FragmentMyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAlertDialogBuilder.setOnClickListener {
            materialAlertDialogBuilder()
        }
    }

    private fun materialAlertDialogBuilder() =
        MaterialAlertDialogBuilder(context!!)
            .setTitle("My title")
            .setMessage("My Message")
            .setNegativeButton("Negative") { _, _ ->
                Toast.makeText(this.context , "Negative has been chosen", Toast.LENGTH_LONG).show()
            }
            .setPositiveButton("Posetive") { _, _ ->
                Toast.makeText(this.context, "Positive has been chosen", Toast.LENGTH_LONG).show()
            }
            .setNeutralButton("Neutral"){_, _ ->
                Toast.makeText(this.context, "Neutral has been chosen", Toast.LENGTH_LONG).show()
            }
            .show()
}