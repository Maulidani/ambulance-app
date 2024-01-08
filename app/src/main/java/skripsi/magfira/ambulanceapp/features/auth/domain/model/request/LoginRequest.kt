package skripsi.magfira.ambulanceapp.features.auth.domain.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    val password: String
)

//class LoginRequest {
//    @SerializedName("username")
//    @Expose
//    lateinit var username: String
//
//    @SerializedName("password")
//    @Expose
//    lateinit var password: String
//
//}