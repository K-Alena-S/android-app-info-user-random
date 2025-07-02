package com.example.android_app_info_user_random.navigation

import android.net.Uri
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_app_info_user_random.data.models.UserDTO
import com.example.android_app_info_user_random.ui.UserScreen
import com.example.android_app_info_user_random.ui.UserDetailScreen
import com.example.android_app_info_user_random.viewmodel.UserViewModel
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val sharedViewModel: UserViewModel = koinViewModel()

    val listState = rememberLazyListState()

    NavHost(navController = navController, startDestination = "userList") {
        composable("userList") {
            UserScreen(
                viewModel = sharedViewModel,
                navController = navController,
                listState = listState
            )
        }
        composable("userDetail/{userJson}") { backStackEntry ->
            val encodedJson = backStackEntry.arguments?.getString("userJson")
            val json = Uri.decode(encodedJson)
            val user = Gson().fromJson(json, UserDTO::class.java)
            UserDetailScreen(user = user, onBack = { navController.popBackStack() })
        }
    }
}