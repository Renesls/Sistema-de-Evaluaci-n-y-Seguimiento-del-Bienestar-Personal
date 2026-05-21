package com.example.sistemabienestarpersonal.model

data class Option(
    val text: String,
<<<<<<< HEAD
    val weight: Int // Impacto en la categoría (1-5)
=======
    val weight: Int
>>>>>>> Mario
)

data class Scenario(
    val id: Int,
    val situation: String,
<<<<<<< HEAD
    val category: String, // ej: "Manejo de Estrés", "Toma de Decisiones", "Estado Emocional"
=======
    val category: String,
>>>>>>> Mario
    val options: List<Option>
)

data class EvaluationResult(
    val id: Int,
    val date: String,
<<<<<<< HEAD
    val scores: Map<String, Int>,
    val generalInterpretation: String
=======
    val score: Int,
    val interpretation: String
>>>>>>> Mario
)
