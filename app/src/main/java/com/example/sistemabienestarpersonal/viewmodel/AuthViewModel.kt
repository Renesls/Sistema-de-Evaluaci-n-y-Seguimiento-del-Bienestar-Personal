package com.example.sistemabienestarpersonal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemabienestarpersonal.data.api.AuthApiService
import com.example.sistemabienestarpersonal.data.api.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authApiService = AuthApiService()

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    var token = mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        requestAuth(
            request = { authApiService.login(email = email, password = password) },
            onSuccess = onSuccess
        )
    }

    fun register(name: String, email: String, password: String, onSuccess: () -> Unit) {
        requestAuth(
            request = { authApiService.register(name = name, email = email, password = password) },
            onSuccess = onSuccess
        )
    }

    fun clearError() {
        errorMessage.value = null
    }

    private fun requestAuth(request: suspend () -> AuthResult, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null

            val result = withContext(Dispatchers.IO) { request() }
            when (result) {
                is AuthResult.Success -> {
                    token.value = result.token
                    isLoading.value = false
                    onSuccess()
                }
                is AuthResult.Error -> {
                    errorMessage.value = result.message
                    isLoading.value = false
                }
            }
        }
    }
}
