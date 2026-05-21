package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< HEAD
import com.example.sistemabienestarpersonal.data.HistoryManager
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.SoftBackground
=======
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.SoftBackground
import com.example.sistemabienestarpersonal.viewmodel.WellbeingViewModel
>>>>>>> Mario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
<<<<<<< HEAD
    onBack: () -> Unit
) {
    val history = HistoryManager.history
=======
    viewModel: WellbeingViewModel,
    onBack: () -> Unit
) {
    // Obtenemos la lista del ViewModel
    val history = viewModel.historialResultados
>>>>>>> Mario

    Scaffold(
        containerColor = SoftBackground,
        topBar = {
            TopAppBar(
                title = { Text("Historial de Evolución", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        if (history.isEmpty()) {
            Box(
<<<<<<< HEAD
                modifier = Modifier.fillMaxSize().padding(padding),
=======
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
>>>>>>> Mario
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No hay evaluaciones registradas aún.",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(history) { result ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = result.date,
                                    fontSize = 12.sp,
                                    color = BluePrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
<<<<<<< HEAD
                                    text = "ID: ${result.id}",
                                    fontSize = 10.sp,
                                    color = Color.LightGray
=======
                                    text = "Pts: ${result.score}",
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Bold
>>>>>>> Mario
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
<<<<<<< HEAD
                                text = result.generalInterpretation,
=======
                                text = result.interpretation,
>>>>>>> Mario
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}
