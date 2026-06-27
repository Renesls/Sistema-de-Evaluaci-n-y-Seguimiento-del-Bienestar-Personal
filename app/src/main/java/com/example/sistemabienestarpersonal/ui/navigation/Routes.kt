package com.example.sistemabienestarpersonal.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object WelcomeRoute

@Serializable
object AuthRoute

@Serializable
object TestRoute

@Serializable
data class ResultRoute(val interpretation: String)

@Serializable
object HistoryRoute

@Serializable
object ProfileRoute

@Serializable
object DashboardRoute
