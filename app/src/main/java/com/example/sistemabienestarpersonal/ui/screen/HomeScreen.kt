package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7FB))
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {

        // Greeting
        Text(
            text = "Buenos Dias",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E2E2E)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Como te sientes hoy?",
            fontSize = 16.sp,
            color = Color.Gray
        )


        Spacer(modifier = Modifier.height(24.dp))

//        // Daily Wellness Card
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(20.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color(0xFFBEE7E8)
//            )
//        ) {
//            Column(modifier = Modifier.padding(20.dp)) {
//                Text(
//                    text = "Today's Focus",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Take 5 minutes to breathe and relax 🧘",
//                    fontSize = 15.sp
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(24.dp))


        Button (
            onClick = { navController.navigate("test") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Empezar Test de Bienestar")
        }


        Spacer(modifier = Modifier.height(30.dp))

        // Quote
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "“Deja para mañana lo que pudiste hacer hace 2 semanas”",
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray
            )
        }
    }
}