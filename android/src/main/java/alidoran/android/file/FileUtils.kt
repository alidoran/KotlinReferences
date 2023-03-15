package alidoran.android.file

import alidoran.android.BuildConfig
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//DO NOT FORGET ADD file_path THEN INSERT IT TO MANIFEST

object FileUtils {

    private const val my_folder = "My-Folder"
    enum class Mime(val value: String){
        Mp4("video/mp4"),
        Jpg("image/jpeg")
    }

    //region create file
    fun createFile(context: Context, mimeType: String): Uri {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
            createFileUnderQ(context, mimeType)
        else createFileUpperQ(context, mimeType)
    }

    @SuppressLint("SimpleDateFormat")
    private fun createFileUnderQ(context: Context, mimeType: String): Uri {
        val folder = File(getChatGalleryFilePath())
        val fileName = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val fileFormat = when (mimeType) {
            Mime.Jpg.value -> "jpg"
            Mime.Mp4.value -> "mp4"
            else -> throw java.lang.IllegalArgumentException()
        }
        var uri: Uri? = null
        if (folder.exists() || folder.mkdirs())
            try {
                val file = File(folder.absolutePath + File.separator + fileName + "." + fileFormat)
                uri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + ".fileprovider",
                    file
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        return uri ?: throw java.lang.IllegalArgumentException()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("SimpleDateFormat")
    private fun createFileUpperQ(context: Context, mimeType: String): Uri {
        val timeStamp = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val fileSuffix = ""
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp$fileSuffix")
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, getChatGalleryFilePath())
        contentValues.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis())
        return context.contentResolver
            .insert(MediaStore.Files.getContentUri("external"), contentValues)!!
    }
    //endregion

    //region file list
    fun getUriFileList(
        filePath: String,
        contentResolver: ContentResolver
    ): ArrayList<FileModel> {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
            getUriFileListUnderQ(filePath)
        else
            getUriFileListUpperQ(filePath, contentResolver)
    }

    private fun getUriFileListUnderQ(filePath: String): ArrayList<FileModel> {
        val f = File(filePath)
        val fileArray = f.listFiles()
        val fileModelList = ArrayList<FileModel>()
        var fileModel: FileModel
        if (fileArray != null) for (file in fileArray) {
            fileModel = FileModel(
                name = file.name,
                uriAddress = file.toUri().toString(),
                mimeType = getMime(file.name),
                duration = getVideoFileDuration(file)
            )
            fileModelList.add(fileModel)
        }
        return fileModelList
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    private fun getUriFileListUpperQ(filePath: String, contentResolver: ContentResolver):
            ArrayList<FileModel> {

        val result = ArrayList<FileModel>()
        val contentUri = MediaStore.Files.getContentUri("external")
        val selection = MediaStore.MediaColumns.RELATIVE_PATH + "=?"
        val selectionArgs = arrayOf(filePath)
        val cursor = contentResolver.query(
            contentUri, null, selection, selectionArgs, null
        )
        if (cursor == null || cursor.count == 0) {
            cursor?.close()
            return result
        } else {
            while (cursor.moveToNext()) {
                cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)
                val fileModel = FileModel(
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)),
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_ADDED)),
                    ContentUris.withAppendedId(
                        contentUri,
                        cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                    ).toString(),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DURATION)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.SIZE))
                )
                if (fileModel.size == null){
                    File(fileModel.uri.path.toString()).delete()
                    continue
                }
                if (fileModel.mimeType == Mime.Jpg.value ||
                    fileModel.mimeType == Mime.Mp4.value
                )
                    result.add(fileModel)
            }
            return result
        }
    }
    //endregion

    private fun getMime(fileName: String): String {
        return if (fileName.endsWith(Mime.Jpg.name.lowercase())) Mime.Jpg.value
        else if (fileName.endsWith(Mime.Mp4.name.lowercase())) Mime.Mp4.value
        else ""
    }

    private fun getVideoFileDuration(file: File): String? {
        return if (getMime(file.name) == Mime.Mp4.value) {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(file.path)
            val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            retriever.release()
            return duration
        } else null
    }

    fun getChatGalleryFilePath(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + "Documents" + File.separator + my_folder
        else Environment.DIRECTORY_DOCUMENTS + File.separator + my_folder + File.separator
    }
}