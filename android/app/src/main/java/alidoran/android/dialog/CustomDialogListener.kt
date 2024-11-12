package alidoran.android.dialog

import androidx.fragment.app.DialogFragment

interface CustomDialogListener {
    fun onDialogPositiveClick(dialog: DialogFragment)
    fun onDialogNegativeClick(dialog: DialogFragment)
}