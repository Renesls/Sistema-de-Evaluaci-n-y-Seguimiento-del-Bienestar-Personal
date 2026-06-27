package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.SoftBackground
import com.example.sistemabienestarpersonal.viewmodel.WellbeingViewModel

@Composable
fun DashboardScreen(viewModel: WellbeingViewModel) {
    // Load stats when entering screen
    LaunchedEffect(Unit) {
        viewModel.loadDashboardStats()
    }
    val stats = viewModel.dashboardStats.value

    Scaffold(containerColor = SoftBackground) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Resumen de Bienestar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BluePrimary
            )
            // Daily average card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Promedio Diario", fontWeight = FontWeight.SemiBold)
                    Text("${stats.dailyAvg.format(2)} pts", fontSize = 20.sp)
                }
            }
            // Weekly average card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Promedio Semanal", fontWeight = FontWeight.SemiBold)
                    Text("${stats.weeklyAvg.format(2)} pts", fontSize = 20.sp)
                }
            }
            // Monthly average card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Promedio Mensual", fontWeight = FontWeight.SemiBold)
                    Text("${stats.monthlyAvg.format(2)} pts", fontSize = 20.sp)
                }
            }
        }
    }
}

private fun Double.format(digits: Int): String = "%#.${digits}f".format(this)
