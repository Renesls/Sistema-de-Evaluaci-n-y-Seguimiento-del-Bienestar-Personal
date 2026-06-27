package com.example.sistemabienestarpersonal.data.repository

import com.example.sistemabienestarpersonal.model.EvaluationResult
import java.text.SimpleDateFormat
import java.util.*

/**
 * Data class that holds aggregated statistics for the dashboard.
 * dailyAvg – average score of results from today.
 * weeklyAvg – average score of the last 7 days.
 * monthlyAvg – average score of the last 30 days.
 * dailyCounts – map of hour (0‑23) to number of tests taken.
 * weeklyCounts – map of day of week (Mon‑Sun) to count.
 */
data class AggregatedStats(
    val dailyAvg: Double,
    val weeklyAvg: Double,
    val monthlyAvg: Double,
    val dailyCounts: Map<Int, Int>,
    val weeklyCounts: Map<String, Int>
)
