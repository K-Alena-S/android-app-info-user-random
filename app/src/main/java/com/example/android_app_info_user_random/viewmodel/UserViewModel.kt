package com.example.android_app_info_user_random.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_info_user_random.data.models.UserDTO
import com.example.android_app_info_user_random.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDTO>>(emptyList())
    val users: StateFlow<List<UserDTO>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.fetchUsers()
                _users.value = response.results
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }
}