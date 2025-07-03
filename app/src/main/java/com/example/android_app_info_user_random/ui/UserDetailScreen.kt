package com.example.android_app_info_user_random.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_app_info_user_random.data.models.UserDTO
import com.example.android_app_info_user_random.ui.open.*
import com.example.android_app_info_user_random.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: UserDTO, onBack: () -> Unit) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { UserDetailTopBar(onBack) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            UserProfileSection(user, context)
            Spacer(modifier = Modifier.height(16.dp))
            UserInfoSection(user, context)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.user_title)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = (R.string.back_button.toString())
                )
            }
        }
    )
}

@Composable
fun UserProfileSection(user: UserDTO, context: android.content.Context) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = user.picture.large,
            contentDescription = stringResource(R.string.user_photo),
            modifier = Modifier.size(128.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = stringResource(R.string.user_full_name) + ": ${user.name.title} ${user.name.first} ${user.name.last}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "${stringResource(R.string.city)}: ${user.location.city}")
            Text(
                text = "${stringResource(R.string.phone)}:\n${user.phone}",
                modifier = Modifier.clickable { openPhoneDialer(context, user.phone) }
            )
        }
    }
}

@Composable
fun UserInfoSection(user: UserDTO, context: android.content.Context) {
    Column {
        Text(text = "${stringResource(R.string.country)}: ${user.location.country}")
        Text(text = "${stringResource(R.string.state)}: ${user.location.state}")
        Text(text = "${stringResource(R.string.postcode)}: ${user.location.postcode}")
        Text(text = "${stringResource(R.string.address)}: ${user.location.street.number} ${user.location.street.name}")
        Text(
            text = "${stringResource(R.string.coordinates)}: ${user.location.coordinates.latitude}, ${user.location.coordinates.longitude}",
            modifier = Modifier.clickable {
                val lat = user.location.coordinates.latitude
                val lon = user.location.coordinates.longitude
                if (lat != null && lon != null) {
                    openMap(context, lat, lon)
                }
            }
        )
        Text(text = "${stringResource(R.string.timezone)}: ${user.location.timezone.description} (${user.location.timezone.offset})")
        Text(text = "${stringResource(R.string.nationality)}: ${user.nat}")
        Text(text = "${stringResource(R.string.birth_date)}: ${user.dob.date.take(10)}")
        Text(text = "${stringResource(R.string.age)}: ${user.dob.age}")
        Text(
            text = "${stringResource(R.string.email)}: ${user.email}",
            modifier = Modifier.clickable { openEmail(context, user.email) }
        )
        Text(text = "${stringResource(R.string.registration_date)}: ${user.registered.date.take(10)}")
        Text(text = "${stringResource(R.string.id)}: ${user.id.name} ${user.id.value ?: ""}")
    }
}