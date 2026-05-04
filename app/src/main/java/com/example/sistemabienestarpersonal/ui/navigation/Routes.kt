package com.example.sistemabienestarpersonal.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object WelcomeRoute

@Serializable
object TestRoute

@Serializable
data class ResultRoute(val scoreSummary: String)

@Serializable
object HistoryRoute
