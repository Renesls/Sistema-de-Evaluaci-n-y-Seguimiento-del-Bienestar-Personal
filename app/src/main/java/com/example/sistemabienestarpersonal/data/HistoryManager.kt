package com.example.sistemabienestarpersonal.data

import com.example.sistemabienestarpersonal.model.EvaluationResult
import java.util.Collections

object HistoryManager {
    private val _history = mutableListOf<EvaluationResult>()
    val history: List<EvaluationResult> get() = Collections.unmodifiableList(_history)

    fun addResult(result: EvaluationResult) {
        _history.add(0, result)
    }

    fun clearHistory() {
        _history.clear()
    }
}
