package com.example.sistemabienestarpersonal.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class TokenResponse(
    @SerialName("token") val token: String
)

sealed class AuthResult {
    data class Success(val token: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthApiService {
    private val json = Json { ignoreUnknownKeys = true }
    private val baseUrl = "https://grilloworks.pythonanywhere.com"

    suspend fun login(email: String, password: String): AuthResult {
        val body = json.encodeToString(LoginRequest(email = email, password = password))
        return postForToken(endpoint = "login", body = body)
    }

    suspend fun register(name: String, email: String, password: String): AuthResult {
        val body = json.encodeToString(RegisterRequest(name = name, email = email, password = password))
        return postForToken(endpoint = "register", body = body)
    }

    private suspend fun postForToken(endpoint: String, body: String): AuthResult {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("$baseUrl/$endpoint")
                val connection = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    doOutput = true
                    connectTimeout = 10000
                    readTimeout = 10000
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Accept", "application/json")
                }

                connection.outputStream.use { os ->
                    os.write(body.toByteArray(Charsets.UTF_8))
                }

                val statusCode = connection.responseCode
                val responseText = readResponse(connection, statusCode)

                when (statusCode) {
                    HttpURLConnection.HTTP_OK -> {
                        val token = json.decodeFromString<TokenResponse>(responseText).token
                        AuthResult.Success(token)
                    }
                    HttpURLConnection.HTTP_BAD_REQUEST -> {
                        AuthResult.Error("Datos invalidos. Verifica la informacion.")
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        AuthResult.Error("Credenciales incorrectas.")
                    }
                    else -> {
                        AuthResult.Error("Error del servidor ($statusCode).")
                    }
                }
            } catch (e: Exception) {
                //AuthResult.Error("No se pudo conectar con el servidor.")
                e.printStackTrace()
                AuthResult.Error(e.toString())
            }
        }
    }
    private fun readResponse(connection: HttpURLConnection, statusCode: Int): String {
        val stream = if (statusCode in 200..299) connection.inputStream else connection.errorStream
        if (stream == null) return ""

        return BufferedReader(InputStreamReader(stream)).use { reader ->
            buildString {
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    append(line)
                }
            }
        }
    }
}
