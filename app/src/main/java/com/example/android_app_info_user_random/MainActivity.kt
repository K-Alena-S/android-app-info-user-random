package com.example.android_app_info_user_random

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_app_info_user_random.ui.UserScreen
import com.example.android_app_info_user_random.ui.theme.AndroidappinfouserrandomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidappinfouserrandomTheme {
                UserScreen()
            }
        }
    }
}


