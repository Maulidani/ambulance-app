package skripsi.magfira.ambulanceapp.util

object MimeFileType {

    // MIME type
    val FILE_TYPE_SURAT_IZIN_YAYASAN = arrayOf(
        "application/pdf",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    )
    val FILE_TYPE_ALLOWED = setOf(
        "image/jpeg",
        "image/png",
        "image/jpg",
        "application/pdf",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    )

}