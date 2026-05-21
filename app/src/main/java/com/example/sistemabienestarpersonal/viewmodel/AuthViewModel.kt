package com.example.sistemabienestarpersonal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemabienestarpersonal.data.api.AuthApiService
import com.example.sistemabienestarpersonal.data.api.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {
    private val authApiService = AuthApiService()

    var isLoading = mutableStateOf(false)
        private set

    // Única fuente de la verdad para el mensaje de error de toda la pantalla de Auth
    var errorMessage = mutableStateOf<String?>(null)
        private set

    var token = mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        val trimmedEmail = email.trim()
        
        // Reglas de validación movidas al ViewModel
        if (trimmedEmail.isBlank() || !trimmedEmail.contains("@")) {
            errorMessage.value = "Ingresa un correo válido."
            return
        }
        if (password.length < 6) {
            errorMessage.value = "La contraseña debe tener al menos 6 caracteres."
            return
        }

        requestAuth(
            request = { authApiService.login(email = trimmedEmail, password = password) },
            onSuccess = onSuccess
        )
    }

    fun register(name: String, email: String, password: String, confirmPassword: String, onSuccess: () -> Unit) {
        val trimmedName = name.trim()
        val trimmedEmail = email.trim()

        // Reglas de validación movidas al ViewModel
        if (trimmedName.isBlank()) {
            errorMessage.value = "Ingresa tu nombre para crear tu cuenta."
            return
        }
        if (trimmedEmail.isBlank() || !trimmedEmail.contains("@")) {
            errorMessage.value = "Ingresa un correo válido."
            return
        }
        if (password.length < 6) {
            errorMessage.value = "La contraseña debe tener al menos 6 caracteres."
            return
        }
        if (password != confirmPassword) {
            errorMessage.value = "Las contraseñas no coinciden."
            return
        }

        requestAuth(
            request = { authApiService.register(name = trimmedName, email = trimmedEmail, password = password) },
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
