package skripsi.magfira.ambulanceapp.features.auth.domain.model.request

import android.content.Context
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.util.MultipartUtils.createStringRequestBody
import skripsi.magfira.ambulanceapp.util.MultipartUtils.createUriPart
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_DOCUMENT_YAYASAN

fun RegisterYayasanRequest(
    roles: String,
    statusAccount: String,
    name: String,
    email: String,
    phone: String,
    address: String,
    username: String,
    password: String,
    confirmPassword: String,
    fileUri: Uri,
    context: Context
): Pair<Map<String, RequestBody>, MultipartBody.Part> {
    val partMap = mutableMapOf<String, RequestBody>()
    partMap["roles"] = createStringRequestBody(roles)
    partMap["status_akun"] = createStringRequestBody(statusAccount)
    partMap["name"] = createStringRequestBody(name)
    partMap["email"] = createStringRequestBody(email)
    partMap["alamat"] = createStringRequestBody(address)
    partMap["no_telp"] = createStringRequestBody(phone)
    partMap["username"] = createStringRequestBody(username)
    partMap["password"] = createStringRequestBody(password)
    partMap["password_confirmation"] = createStringRequestBody(confirmPassword)

    val filePart = createUriPart(context, fileUri, NAME_KEY_DOCUMENT_YAYASAN)

    return partMap to filePart
}
