package com.example.sistemabienestarpersonal.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
<<<<<<< HEAD
import androidx.compose.ui.graphics.Color.Companion.White
=======
import androidx.compose.ui.graphics.Color
>>>>>>> Mario
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemabienestarpersonal.ui.theme.BluePrimary
import com.example.sistemabienestarpersonal.ui.theme.BlueSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit
) {
    val scale = remember { Animatable(0.5f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
<<<<<<< HEAD
        // Animación de entrada
=======
>>>>>>> Mario
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(1000)
            )
        }
<<<<<<< HEAD

=======
>>>>>>> Mario
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(1200)
            )
        }
<<<<<<< HEAD

        // Espera tipo splash
=======
>>>>>>> Mario
        delay(2500)
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BluePrimary, BlueSecondary)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale.value)
                    .alpha(alpha.value)
                    .clip(CircleShape)
<<<<<<< HEAD
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                // Icono simple con texto
=======
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
>>>>>>> Mario
                Text(
                    text = "🧘",
                    fontSize = 70.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bienestar Personal",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
<<<<<<< HEAD
                color = White,
=======
                color = Color.White,
>>>>>>> Mario
                modifier = Modifier.alpha(alpha.value)
            )

            Text(
                text = "Tu herramienta de evolución",
                fontSize = 18.sp,
<<<<<<< HEAD
                color = White.copy(alpha = 0.8f),
=======
                color = Color.White.copy(alpha = 0.8f),
>>>>>>> Mario
                modifier = Modifier.alpha(alpha.value)
            )

            Spacer(modifier = Modifier.height(60.dp))

            CircularProgressIndicator(
<<<<<<< HEAD
                color = White,
=======
                color = Color.White,
>>>>>>> Mario
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }
}
