package com.example.android_app_info_user_random.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_info_user_random.data.models.UserDTO
import com.example.android_app_info_user_random.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _listState = MutableStateFlow<LazyListState?>(null)

    fun setListState(state: LazyListState) {
        _listState.value = state
    }

    private val _users = MutableStateFlow<List<UserDTO>>(emptyList())
    val users: StateFlow<List<UserDTO>> = _users.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    fun loadUsers(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val userList = repository.fetchUsers(forceRefresh)
                _users.value = userList
            } catch (e: Exception) {
                _error.emit( "${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}