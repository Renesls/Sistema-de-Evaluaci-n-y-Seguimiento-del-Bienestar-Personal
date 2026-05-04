package com.example.sistemabienestarpersonal.model

data class Option(
    val text: String,
    val weight: Int // Impacto en la categoría (1-5)
)

data class Scenario(
    val id: Int,
    val situation: String,
    val category: String, // ej: "Manejo de Estrés", "Toma de Decisiones", "Estado Emocional"
    val options: List<Option>
)

data class EvaluationResult(
    val id: Int,
    val date: String,
    val scores: Map<String, Int>,
    val generalInterpretation: String
)
