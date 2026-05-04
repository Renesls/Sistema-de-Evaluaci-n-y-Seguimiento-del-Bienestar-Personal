package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sistemabienestarpersonal.model.Answer
import com.example.sistemabienestarpersonal.model.Question
import com.example.sistemabienestarpersonal.model.calculateResults
import kotlinx.coroutines.delay

@Composable
fun TestScreen(navController: NavController) {

    val questions = listOf(
        Question("Me siento estresado", "Estrés"),
        Question("Duermo bien", "Energía"),
        Question("Tengo motivación", "Motivación")
    )

    val answers = remember { mutableStateMapOf<Question, Int>() }
    val allAnswered = answers.size == questions.size

    var showValidationError by remember { mutableStateOf(false) }
    if (showValidationError) {
        LaunchedEffect(showValidationError) {
            delay(2000) // 2 seconds
            showValidationError = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Test de Bienestar", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(questions) { question ->

                val selectedValue = answers[question]

                Column(modifier = Modifier.padding(8.dp)) {

                    Text(question.text)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        (1..5).forEach { value ->
                            val isSelected = selectedValue == value
                            Button(
                                onClick = {
                                    answers[question] = value
                                },
                                modifier = Modifier.padding(4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.surface
                                    }
                                )

                            ) {
                                Text(
                                    text = "$value",
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onPrimary
                                    }
                                    else {
                                        MaterialTheme.colorScheme.onSurface
                                    }

                                )

                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!allAnswered) {
                    showValidationError = true
                    return@Button
                }


                val answerList = answers.map {
                    Answer(it.key, it.value)
                }

                val results = calculateResults(answerList)

                val resultString = results.entries.joinToString(",") {
                    "${it.key}:${it.value}"
                }

                navController.navigate("result/$resultString")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = allAnswered
        ) {
            Text("Finalizar")
        }

        if (showValidationError) {
            Text(
                text = "Por favor responde todas las preguntas",
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}