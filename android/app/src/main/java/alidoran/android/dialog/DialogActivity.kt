package alidoran.android.dialog

import alidoran.android.databinding.ActivityDialogBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogActivity : AppCompatActivity(), CustomDialogListener {

    private lateinit var binding: ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() {
        binding.btnAlertDialog.setOnClickListener {
            materialAlertDialogBuilder()
        }

        binding.btnSimpleDialog.setOnClickListener {
            simpleDialogBuilder()
        }

        binding.btnConfirmationDialog.setOnClickListener {
            confirmationDialogBuilder()
        }

        binding.btnFullScreenDialog.setOnClickListener {
            fullScreenDialogBuilder(false)
        }

        binding.btnFullScreenDialogLarge.setOnClickListener {
            fullScreenDialogBuilder(true)
        }

    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }

    //region (full screen/ custom) dialog builder
    private fun fullScreenDialogBuilder(isLargeLayout : Boolean) {
            val fragmentManager = supportFragmentManager
            val newFragment = CustomDialogFragment()
            if (isLargeLayout) {
                // The device is using a large layout, so show the fragment as a dialog
                newFragment.show(fragmentManager, "dialog")
            } else {
                // The device is smaller, so show the fragment fullscreen
                val transaction = fragmentManager.beginTransaction()
                // For a little polish, specify a transition animation
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                // To make it fullscreen, use the 'content' root view as the container
                // for the fragment, which is always the root view for the activity
                transaction
                    .add(android.R.id.content, newFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun confirmationDialogBuilder() {
        val singleItems = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItem = 1

        MaterialAlertDialogBuilder(this)
            .setTitle("My title")
            .setNeutralButton("Neutral") { _, which ->
                toast("Neutral with row number $which has been selected")
            }
            .setPositiveButton("Positive") { _, which ->
                toast("Positive with row number $which has been selected")
            }
            .setNegativeButton("Negative"){_, which ->
                toast("Negative with row number $which has been selected")
            }
            // Single-choice items (initialized with checked item)
            .setSingleChoiceItems(singleItems, checkedItem) { _, which ->
                toast("row $which has benn selected")
            }
            .show()
    }
    //endregion

    private fun simpleDialogBuilder() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        MaterialAlertDialogBuilder(this)
            .setTitle("My title")
            .setItems(items) { _, which ->
                    toast("Item number $which has been chosen")
            }
            .show()
    }

    private fun materialAlertDialogBuilder() =
        MaterialAlertDialogBuilder(this)
            .setTitle("My title")
            .setMessage("My Message")
            .setNegativeButton("Negative") { _, _ ->
                toast("Negative has been chosen")
            }
            .setPositiveButton("Positive") { _, _ ->
                toast("Positive has been chosen")
            }
            .setNeutralButton("Neutral") { _, _ ->
                toast("Neutral has been chosen")
            }
            .show()

    private fun toast(toastString: String) =
        Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()

}