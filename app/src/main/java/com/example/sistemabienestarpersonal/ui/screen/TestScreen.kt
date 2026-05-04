package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemabienestarpersonal.data.HistoryManager
import com.example.sistemabienestarpersonal.model.EvaluationResult
import com.example.sistemabienestarpersonal.model.Option
import com.example.sistemabienestarpersonal.model.Scenario
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.SoftBackground
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TestScreen(
    onFinish: (String) -> Unit,
    onBack: () -> Unit
) {
    val scenarios = remember {
        listOf(
            Scenario(
                1,
                "Tienes una entrega importante en 2 horas y tu computadora se reinicia inesperadamente.",
                "Manejo de Estrés",
                listOf(
                    Option("Mantengo la calma y busco una solución inmediata.", 5),
                    Option("Me frustro pero trato de avanzar en lo que puedo.", 3),
                    Option("Entro en pánico y no sé qué hacer.", 1)
                )
            ),
            Scenario(
                2,
                "Un compañero de trabajo te da una crítica constructiva pero directa sobre tu desempeño.",
                "Estado Emocional",
                listOf(
                    Option("Agradezco el feedback y lo analizo objetivamente.", 5),
                    Option("Me siento un poco herido pero trato de mejorar.", 3),
                    Option("Me lo tomo personal y me desmotivo.", 1)
                )
            ),
            Scenario(
                3,
                "Debes elegir entre dos oportunidades: una segura y otra arriesgada pero con mayor crecimiento.",
                "Toma de Decisiones",
                listOf(
                    Option("Analizo pros y contras y tomo el riesgo calculado.", 5),
                    Option("Me cuesta decidir y pido muchas opiniones.", 3),
                    Option("Elijo lo más seguro por miedo al fracaso.", 1)
                )
            ),
            Scenario(
                4,
                "Te encuentras en medio de una discusión acalorada con un amigo.",
                "Estado Emocional",
                listOf(
                    Option("Escucho su punto de vista y trato de mediar.", 5),
                    Option("Respondo a la defensiva pero trato de calmarme después.", 3),
                    Option("Grito o me retiro molesto sin hablar.", 1)
                )
            )
        )
    }

    var currentScenarioIndex by remember { mutableStateOf(0) }
    var responses by remember { mutableStateOf(mutableMapOf<String, Int>()) }

    val currentScenario = scenarios[currentScenarioIndex]
    val progress = (currentScenarioIndex + 1).toFloat() / scenarios.size

    Scaffold(
        containerColor = SoftBackground,
        topBar = {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = BluePrimary,
                trackColor = BluePrimary.copy(alpha = 0.2f)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Escenario ${currentScenarioIndex + 1} de ${scenarios.size}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = BluePrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentScenario.category,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = currentScenario.situation,
                    modifier = Modifier.padding(24.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "¿Cómo reaccionarías?",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            currentScenario.options.forEach { option ->
                Button(
                    onClick = {
                        val category = currentScenario.category
                        responses[category] = (responses[category] ?: 0) + option.weight

                        if (currentScenarioIndex < scenarios.size - 1) {
                            currentScenarioIndex++
                        } else {
                            // Finalizar y guardar
                            val result = EvaluationResult(
                                id = (0..10000).random(),
                                date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()),
                                scores = responses,
                                generalInterpretation = generateInterpretation(responses)
                            )
                            HistoryManager.addResult(result)
                            onFinish(result.generalInterpretation)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = option.text,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun generateInterpretation(scores: Map<String, Int>): String {
    val total = scores.values.sum()
    return when {
        total >= 15 -> "Tienes un perfil altamente resiliente y equilibrado. Manejas la presión con sabiduría."
        total >= 10 -> "Tu perfil es estable, aunque hay áreas de oportunidad en situaciones de alta presión."
        else -> "Tiendes a reaccionar de forma impulsiva o bajo estrés. Trabaja en la pausa antes de actuar."
    }
}