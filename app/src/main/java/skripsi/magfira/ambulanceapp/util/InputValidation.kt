package skripsi.magfira.ambulanceapp.util

object InputValidation {
    fun containsNoSpaces(text: String): Boolean {
        return !text.contains(" ")
    }

    fun isValidEmailFormat(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.matches(emailRegex)
    }

    fun isValidPhoneFormat(number: String): Boolean {
        return number.matches(Regex("\\d+"))
    }
}