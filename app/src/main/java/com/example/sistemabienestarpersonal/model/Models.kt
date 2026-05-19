package com.example.sistemabienestarpersonal.model

data class Option(
    val text: String,
    val weight: Int
)

data class Scenario(
    val id: Int,
    val situation: String,
    val category: String,
    val options: List<Option>
)

data class EvaluationResult(
    val id: Int,
    val date: String,
    val score: Int,
    val interpretation: String
)
