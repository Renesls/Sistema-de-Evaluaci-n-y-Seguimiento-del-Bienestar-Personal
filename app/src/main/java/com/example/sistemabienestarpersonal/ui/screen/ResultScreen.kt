package com.example.sistemabienestarpersonal.ui.screen

<<<<<<< HEAD
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
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
=======
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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

@Composable
fun ResultScreen(
    interpretation: String,
    onNavigateHome: () -> Unit
) {
    Scaffold(
        containerColor = SoftBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tu Perfil Actual",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BluePrimary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📊", fontSize = 60.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = interpretation,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 26.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onNavigateHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver al Inicio", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
>>>>>>> Rene
        }
    }
}