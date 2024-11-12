package alidoran.android.file

import android.net.Uri


data class FileModel(
    val id: Long? = null,
    val name: String,
    val dateAdded: Long? = null,
    val uriAddress: String,
    val mimeType: String,
    val duration: String? = null,
    val size: String? = null,
){
    val uri: Uri get() = Uri.parse(uriAddress)
}