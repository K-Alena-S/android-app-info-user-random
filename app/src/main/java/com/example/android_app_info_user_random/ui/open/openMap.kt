package com.example.android_app_info_user_random.ui.open

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openMap(context: Context, latitude: Float, longitude: Float) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
    val intent = Intent(Intent.ACTION_VIEW, uri)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
