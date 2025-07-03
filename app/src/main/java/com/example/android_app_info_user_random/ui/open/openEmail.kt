package com.example.android_app_info_user_random.ui.open

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openEmail(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:$email".toUri()
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
