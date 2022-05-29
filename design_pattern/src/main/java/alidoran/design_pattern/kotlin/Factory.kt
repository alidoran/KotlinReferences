package alidoran.design_pattern.kotlin

import alidoran.design_pattern.kotlin.DialogFactory.createDialog

enum class DialogType {
    DIALOG_ACCEPT,
    DIALOG_CANCEL,
    DIALOG_DENY
}

sealed class Dialog {
    object CreateChatDialog : Dialog()
    object DeleteMessageDialog : Dialog()
    object EditMessageDialog : Dialog()
}

object DialogFactory {
    fun createDialog(dialogType: DialogType): Dialog {
        return when (dialogType) {
            DialogType.DIALOG_ACCEPT -> Dialog.CreateChatDialog
            DialogType.DIALOG_CANCEL -> Dialog.DeleteMessageDialog
            DialogType.DIALOG_DENY -> Dialog.EditMessageDialog
        }
    }
}

fun callDialog() {
    val mDialog: Dialog = createDialog(DialogType.DIALOG_ACCEPT)
    val m2Dialog: Dialog = createDialog(DialogType.DIALOG_CANCEL)

}

fun main() {
    callDialog()
}

