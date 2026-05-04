package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultScreen(resultData: String, navController: NavController) {

    val results = resultData.split(",").associate {
        val (key, value) = it.split(":")
        key to value.toInt()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Resultados", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        results.forEach { (category, score) ->

            val level = when {
                score <= 2 -> "Bajo"
                score <= 4 -> "Medio"
                else -> "Alto"
            }

            Text("$category: $level ($score)")
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }
    }
}