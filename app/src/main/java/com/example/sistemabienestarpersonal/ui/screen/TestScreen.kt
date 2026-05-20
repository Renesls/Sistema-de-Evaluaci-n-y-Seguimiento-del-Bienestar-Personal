package com.example.sistemabienestarpersonal.ui.screen

<<<<<<< HEAD
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
=======
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.SoftBackground
import com.example.sistemabienestarpersonal.viewmodel.WellbeingViewModel

@Composable
fun TestScreen(
    viewModel: WellbeingViewModel,
    onFinish: (String) -> Unit,
    onBack: () -> Unit
) {
    // Observamos los estados desde el ViewModel (MVVM)
    val isLoading = viewModel.isLoading.value
    val escenarios = viewModel.escenarios
    val indiceActual = viewModel.indiceActual.value

    Scaffold(
        containerColor = SoftBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading || escenarios.isEmpty()) {
                // Estado de Carga (Esperando a la API falsa)
                Spacer(modifier = Modifier.height(100.dp))
                CircularProgressIndicator(color = BluePrimary)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Generando escenarios...", color = Color.Gray)
            } else {
                // Interfaz del Test
                val currentScenario = escenarios[indiceActual]
                val progress = (indiceActual + 1).toFloat() / escenarios.size

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = BluePrimary,
                    trackColor = BluePrimary.copy(alpha = 0.2f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Escenario ${indiceActual + 1} de ${escenarios.size}",
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
                            viewModel.seleccionarOpcion(option.weight, onFinish)
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
>>>>>>> Rene
                    }
                }
            }
        }
<<<<<<< HEAD

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
=======
>>>>>>> Rene
    }
}