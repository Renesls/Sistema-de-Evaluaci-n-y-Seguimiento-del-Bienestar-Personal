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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sistemabienestarpersonal.model.Answer
import com.example.sistemabienestarpersonal.model.Question
import com.example.sistemabienestarpersonal.model.calculateResults

@Composable
fun TestScreen(navController: NavController) {

    val questions = listOf(
        Question("Me siento estresado", "Estrés"),
        Question("Duermo bien", "Energía"),
        Question("Tengo motivación", "Motivación")
    )

    var answers by remember { mutableStateOf(mutableListOf<Answer>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Test de Bienestar", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(questions) { question ->

                var selected by remember { mutableStateOf(0) }

                Column(modifier = Modifier.padding(8.dp)) {

                    Text(question.text)

                    Row {
                        (1..5).forEach { value ->
                            Button(
                                onClick = {
                                    selected = value

                                    answers.removeAll { it.question == question }
                                    answers.add(Answer(question, value))
                                },
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text("$value")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val results: Map<String, Int> = calculateResults(answers)

                val builder = StringBuilder()

                for (entry in results) {
                    builder.append(entry.key)
                    builder.append(":")
                    builder.append(entry.value)
                    builder.append(",")
                }

                val resultString = builder.toString().dropLast(1)

                navController.navigate("result/$resultString")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finalizar")
        }
    }
}