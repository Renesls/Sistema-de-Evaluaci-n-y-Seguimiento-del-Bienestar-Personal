package com.example.sistemabienestarpersonal.model

fun calculateResults(answers: List<Answer>): Map<String, Int> {
    val grouped = answers.groupBy { it.question.category }

    return grouped.mapValues { entry ->
        entry.value.map { it.value }.average().toInt()
    }
}