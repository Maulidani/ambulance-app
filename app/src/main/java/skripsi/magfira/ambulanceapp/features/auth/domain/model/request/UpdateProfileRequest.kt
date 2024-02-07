package skripsi.magfira.ambulanceapp.features.auth.domain.model.request

data class UpdateProfileRequest(
    val name: String? = null,
    val email: String? = null,
    val no_telp: String? = null,
    val alamat: String? = null,
    val username: String? = null,
    val password: String? = null,
    val password_confirmation: String? = null,
    val _method: String = "PATCH"
) {
    fun toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        name?.takeIf { it.isNotEmpty() }?.let { map["name"] = it }
        email?.takeIf { it.isNotEmpty() }?.let { map["email"] = it }
        no_telp?.takeIf { it.isNotEmpty() }?.let { map["no_telp"] = it }
        alamat?.takeIf { it.isNotEmpty() }?.let { map["alamat"] = it }
        username?.takeIf { it.isNotEmpty() }?.let { map["username"] = it }
        password?.takeIf { it.isNotEmpty() }?.let { map["password"] = it }
        password_confirmation?.takeIf { it.isNotEmpty() }?.let { map["password_confirmation"] = it }
        map["_method"] = _method
        return map
    }
}
