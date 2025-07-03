package com.example.android_app_info_user_random.ui.open

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openPhoneDialer(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phoneNumber".toUri()
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }

}
