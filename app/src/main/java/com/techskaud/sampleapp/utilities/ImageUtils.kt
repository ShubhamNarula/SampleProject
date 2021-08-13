package com.techskaud.sampleapp.utilities

import android.content.*
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Build.ID
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import android.content.ContentResolver

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.techskaud.sampleapp.BaseApplication
import java.io.*


object ImageUtils {

    /*
    *
    * To get image in multipart body
    * */
    fun convertImageInMultipart(imagePath: String, thumbImage: String): Pair<MultipartBody.Part, MultipartBody.Part> {
        val file = File(imagePath)
        val requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        val fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        val thumbFile = File(thumbImage)
        val requestBodyThumb = RequestBody.create(MediaType.parse("*/*"), thumbFile);
        val fileToUploadThumb = MultipartBody.Part.createFormData(
            "image_thumb",
            thumbFile.getName(),
            requestBodyThumb
        );
        return  Pair(fileToUpload, fileToUploadThumb)
    }



    fun getPath(uri: Uri?, context: Context): String? {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor: Cursor =
            context.getContentResolver().query(uri!!, projection, null, null, null) ?: return null
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        cursor.moveToFirst()
        val s: String = cursor.getString(column_index)
        cursor.close()
        return s
    }

    @Nullable
    fun createCopyAndReturnRealPath(
         uri: Uri
    ): String? {
        val contentResolver = BaseApplication.getContext().contentResolver ?: return null

        // Create file path inside app's data dir
        val filePath = (BaseApplication.getContext().applicationInfo.dataDir.toString() + File.separator
                + System.currentTimeMillis())
        val file = File(filePath)
        try {
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (ignore: IOException) {
            return null
        }
        return file.absolutePath
    }


    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @author paulburke
     */
     fun getPDFPath(uri: Uri?, context: Context): String? {
        var filePath = ""
        val fileId: String = DocumentsContract.getDocumentId(uri)
        // Split at colon, use second item in the array
        // Split at colon, use second item in the array
        val id = fileId.split(":").toTypedArray()[1]
        val column = arrayOf(MediaStore.Images.Media.DATA)
        val selector = MediaStore.Images.Media._ID + "=?"
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            column, selector, arrayOf(id), null
        )
        val columnIndex = cursor!!.getColumnIndex(column[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }


    fun getFileName(fileUri: Uri, context: Context): String {

        var name = ""
        val returnCursor = context.contentResolver.query(fileUri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }

        return name
    }



}