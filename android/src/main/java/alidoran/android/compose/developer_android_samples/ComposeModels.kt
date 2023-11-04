package alidoran.android.compose.developer_android_samples

import androidx.annotation.DrawableRes

data class ComposeMessageModel(val author: String, val body: String)
data class ComposePictureModel(@DrawableRes val image:Int, val title: String)

fun generateComposeMessageList(): List<ComposeMessageModel> {
    val messageList = ArrayList<ComposeMessageModel>()
    for (i in 1..100) {
        messageList.add(
            ComposeMessageModel(
                "FirstLine $i",
                "SecondLine $i This is the Second long line to testing a compose list view that includes long data on each row")
        )
    }
    return messageList
}
