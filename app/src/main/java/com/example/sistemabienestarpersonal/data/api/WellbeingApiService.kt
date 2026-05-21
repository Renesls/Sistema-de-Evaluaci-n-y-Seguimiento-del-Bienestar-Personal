package com.example.sistemabienestarpersonal.data.api

import com.example.sistemabienestarpersonal.model.Option
import com.example.sistemabienestarpersonal.model.Scenario
import kotlinx.coroutines.delay

class WellbeingApiService {
    
    // Función que simula una petición a internet (API o IA)
    suspend fun obtenerEscenarios(): List<Scenario> {
        // Simulamos que la red tarda 1.5 segundos en responder
        delay(1500)
        
        // Esta es la lista que en el futuro vendrá de internet o de Gemini IA
        return listOf(
            Scenario(
                id = 1,
                situation = "Se acerca la fecha límite de un proyecto final y tu computadora falla.",
                category = "Manejo de Estrés",
                options = listOf(
                    Option("Mantengo la calma, busco otra PC y aviso al profesor.", weight = 5),
                    Option("Me frustro mucho pero intento resolverlo después de un rato.", weight = 3),
                    Option("Entro en pánico y abandono el proyecto.", weight = 1)
                )
            ),
            Scenario(
                id = 2,
                situation = "El profesor te hace una corrección muy dura frente a la clase.",
                category = "Estado Emocional",
                options = listOf(
                    Option("Acepto la crítica constructiva para mejorar mi trabajo.", weight = 5),
                    Option("Me siento mal, pero trato de no demostrarlo.", weight = 3),
                    Option("Me enojo y le respondo a la defensiva.", weight = 1)
                )
            ),
            Scenario(
                id = 3,
                situation = "Tienes que elegir entre un examen fácil seguro o un proyecto difícil que enseña más.",
                category = "Toma de Decisiones",
                options = listOf(
                    Option("Elijo el proyecto difícil para aprender más.", weight = 5),
                    Option("Lo dudo mucho, pero termino eligiendo el proyecto.", weight = 3),
                    Option("Elijo el examen fácil para asegurar la nota sin esfuerzo.", weight = 1)
                )
            )
        )
    }
}
