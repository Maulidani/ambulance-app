package skripsi.magfira.ambulanceapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object OpenChat {

    fun openWhatsAppChat(context: Context, phoneNumber: String, message: String) {
        // Format the phone number properly (remove spaces, dashes, etc.)
        val formattedPhoneNumber = when {
            phoneNumber.startsWith("0") -> {
                "62${phoneNumber.substring(1)}"
            }
            phoneNumber.startsWith("+62") -> {
                phoneNumber.substring(3)
            }
            else -> {
                phoneNumber
            }
        }

        // Create the intent with the appropriate action
        val intent = Intent(Intent.ACTION_VIEW)

        // Set the data URI for WhatsApp using the formatted phone number
        val data = Uri.parse("https://api.whatsapp.com/send?phone=$formattedPhoneNumber&text=$message")
        intent.data = data

        // Verify that WhatsApp is installed on the device before starting the activity
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Install WhatsApp terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }

}