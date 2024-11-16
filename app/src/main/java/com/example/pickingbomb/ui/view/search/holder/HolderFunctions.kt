package com.example.pickingbomb.ui.view.search.holder

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import androidx.compose.ui.geometry.Offset
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object HolderFunctions {


    fun createImageFile(context: Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    fun getMultipartFromUri(uri: Uri, context: Context): MultipartBody.Part? {
        return context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val requestBody = inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
            val fileName = getFileNameFromUri(context, uri) ?: "image.jpg" // Provide a default name
            MultipartBody.Part.createFormData("file", fileName, requestBody)
        }
    }

    private fun getFileNameFromUri(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1) {
                        return cursor.getString(nameIndex)
                    }
                }
            }
        } else if (uri.scheme == "file") {
            return File(uri.path).name
        }
        return null
    }


}