package com.example.sistemabienestarpersonal.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemabienestarpersonal.data.api.WellbeingApiService
import com.example.sistemabienestarpersonal.data.repository.EvaluationResultRepository
import com.example.sistemabienestarpersonal.model.EvaluationResult
import com.example.sistemabienestarpersonal.model.Scenario
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WellbeingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EvaluationResultRepository(application)
    
    // 1. Instancia de la API falsa
    private val apiService = WellbeingApiService()

    // 2. Estados para la pantalla de evaluación (MVVM)
    // Usamos mutableStateOf porque es lo que ya dominan en clase
    var isLoading = mutableStateOf(false)
        private set
        
    var escenarios = mutableStateListOf<Scenario>()
        private set
        
    var indiceActual = mutableStateOf(0)
        private set
        
    var puntajeAcumulado = mutableStateOf(0)
        private set

    // 3. Estado global (Historial de resultados)
    var historialResultados = mutableStateListOf<EvaluationResult>()
        private set

    // Cargar escenarios usando corrutinas (simulando internet)
    fun cargarEscenarios() {
        viewModelScope.launch {
            isLoading.value = true
            indiceActual.value = 0
            puntajeAcumulado.value = 0
            
            // Llamamos a la API falsa
            val datosDeInternet = apiService.obtenerEscenarios()
            
            escenarios.clear()
            escenarios.addAll(datosDeInternet)
            isLoading.value = false
        }
    }

    // Lógica para cuando el usuario elige una opción
    fun seleccionarOpcion(peso: Int, alTerminar: (String) -> Unit) {
        puntajeAcumulado.value += peso
        
        if (indiceActual.value < escenarios.size - 1) {
            // Pasar a la siguiente pregunta
            indiceActual.value++
        } else {
            // Terminó el test, generar resultado
            val resultadoFinal = generarResultado(puntajeAcumulado.value)
            historialResultados.add(0, resultadoFinal) // Agregar al inicio de la lista
            // Persistir el resultado en DataStore
            viewModelScope.launch { repository.addResult(resultadoFinal) }
            
            alTerminar(resultadoFinal.interpretation)
        }
    }

    // Limpiar el historial (Para la pantalla de Perfil)
    fun borrarHistorial() {
        historialResultados.clear()
        viewModelScope.launch { repository.clearAll() }
    }

    // Lógica privada separada de la Vista (Buena práctica POO/MVVM)
    // Dashboard state
    var dashboardStats = mutableStateOf(com.example.sistemabienestarpersonal.data.repository.AggregatedStats(0,0.0,emptyMap(),emptyMap(),emptyMap()))

    /** Load aggregated stats for the dashboard */
    fun loadDashboardStats() {
        viewModelScope.launch {
            dashboardStats.value = repository.getAggregatedStats()
        }
    }
    private fun generarResultado(puntajeTotal: Int): EvaluationResult {
        val interpretacion = when {
            puntajeTotal >= 12 -> "Tu perfil es altamente resiliente. Manejas muy bien el estrés y tus decisiones son acertadas."
            puntajeTotal >= 8 -> "Tienes un buen nivel de bienestar, pero hay momentos de presión que te pueden afectar."
            else -> "Tiendes a estresarte o actuar de forma impulsiva. Es recomendable buscar técnicas de relajación."
        }
        
        return EvaluationResult(
            id = (1000..9999).random(),
            date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()),
            score = puntajeTotal,
            interpretation = interpretacion
        )
    }
}
