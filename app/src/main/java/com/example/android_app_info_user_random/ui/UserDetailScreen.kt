package com.example.android_app_info_user_random.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.android_app_info_user_random.data.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: UserDTO, onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Пользователь") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = user.picture.large,
                    contentDescription = "Фото пользователя",
                    modifier = Modifier
                        .size(128.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "ФИО: ${user.name.title} ${user.name.first} ${user.name.last}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = "Город: ${user.location.city}")
                    Text(text = "Телефон:\n${user.phone}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(text = "Страна: ${user.location.country}")
                Text(text = "Государство: ${user.location.state}")
                Text(text = "Индекс: ${user.location.postcode}")
                Text(text = "Адрес: ${user.location.street.number} ${user.location.street.name}")
                Text(
                    text = "Координаты: ${user.location.coordinates.latitude}, " +
                            "${user.location.coordinates.longitude}",
                    modifier = Modifier.clickable {
                        val lat = user.location.coordinates.latitude
                        val lon = user.location.coordinates.longitude
                        if (lat != null && lon != null) {
                            openMap(context, lat, lon)
                        }
                    }
                )
                Text(text = "Часовой пояс: ${user.location.timezone.description} (${user.location.timezone.offset})")
                Text(text = "Национальность: ${user.nat}")
                Text(text = "Дата рождения: ${user.dob.date.take(10)}")
                Text(text = "Возраст: ${user.dob.age}")
                Text(text = "Email: ${user.email}")
                Text(text = "Дата регистрации: ${user.registered.date.take(10)}")
                Text(text = "ID: ${user.id.name} ${user.id.value ?: ""}")
            }
        }
    }
}

fun openMap(context: Context, latitude: Float, longitude: Float) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
    val intent = Intent(Intent.ACTION_VIEW, uri)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}