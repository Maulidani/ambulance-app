package skripsi.magfira.ambulanceapp.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import skripsi.magfira.ambulanceapp.util.MimeFileType.FILE_TYPE_ALLOWED
import java.io.InputStream

object MultipartUtils {
    private val TAG = "MultipartUtils"

    // Function to create RequestBody from string
    fun createStringRequestBody(data: String): RequestBody {
        return data.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    // Function to create MultipartBody.Part from URI
//    fun createUriPart(context: Context, uri: Uri, partName: String): MultipartBody.Part {
//        val file = uri.path?.let { File(it) }
//        val filePath = uri.path
//
//        val contentType = getMimeType(context, uri) ?: "*/*"
//        Log.d(TAG, "createUriPart: Selected File MIME Type: $contentType")
//
//        // Check if the file type is allowed
//        if (!isFileTypeAllowed(contentType)) {
//            throw IllegalArgumentException("Invalid file type. Allowed types: jpeg, png, jpg, pdf, doc, docx")
//        }
//
//        val requestBody = file?.asRequestBody(contentType.toMediaTypeOrNull())
//            ?: "No Content".toRequestBody("text/plain".toMediaTypeOrNull())
//        Log.d(TAG, "createUriPart: Request Body Size: ${requestBody.contentLength()}")
//
//        return MultipartBody.Part.createFormData(partName, file?.name ?: filePath, requestBody)
//    }

    fun createUriPart(context: Context, uri: Uri, partName: String): MultipartBody.Part {
        val contentResolver = context.contentResolver

        // Get the InputStream from the ContentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)

        // Get the MIME type of the selected document
        val contentType = getMimeType(context, uri) ?: "*/*"
        Log.d(TAG, "createUriPart: Selected File MIME Type: $contentType")

        // Check if the file type is allowed
        if (!isFileTypeAllowed(contentType)) {
            throw IllegalArgumentException("Invalid file type. Allowed types: jpeg, png, jpg, pdf, doc, docx")
        }

        // Create RequestBody from InputStream
        val requestBody: RequestBody = inputStream?.readBytes()?.toRequestBody(contentType.toMediaTypeOrNull())
            ?: "No Content".toRequestBody("text/plain".toMediaTypeOrNull())
        Log.d(TAG, "createUriPart: Request Body Size: ${requestBody.contentLength()}")

        return MultipartBody.Part.createFormData(partName, getFileName(contentResolver, uri), requestBody)
    }

    // Helper function to get the file name from the ContentResolver
    private fun getFileName(contentResolver: ContentResolver, uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
        val name = cursor?.getString(nameIndex ?: 0) ?: "file"
        cursor?.close()
        return name
    }

    // Helper function to check if the file type is allowed
    private fun isFileTypeAllowed(contentType: String): Boolean {
        val allowedTypes = FILE_TYPE_ALLOWED
        return allowedTypes.contains(contentType)
    }

    private fun getMimeType(context: Context, uri: Uri): String? {
        return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            context.contentResolver.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.lowercase())
        }
    }

}
