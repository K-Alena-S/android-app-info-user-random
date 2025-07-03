package com.example.android_app_info_user_random.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_app_info_user_random.data.models.UserDTO
import coil.compose.AsyncImage
import com.example.android_app_info_user_random.R
import com.google.gson.Gson

@Composable
fun UserItem(user: UserDTO, navController: NavController) {
    val gson = Gson()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val userJson = gson.toJson(user)
                val encodedJson = Uri.encode(userJson)
                navController.navigate("userDetail/$encodedJson")
            },
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            AsyncImage(
                model = user.picture.medium,
                contentDescription = stringResource(R.string.user_photo),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "${user.name.title} ${user.name.first} ${user.name.last}", style = MaterialTheme.typography.bodyLarge)
            Text(text = user.location.city)
            Text(text = user.phone)
        }
    }
}