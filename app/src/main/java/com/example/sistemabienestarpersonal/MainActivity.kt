package com.example.sistemabienestarpersonal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sistemabienestarpersonal.ui.navigation.NavGraph
import com.example.sistemabienestarpersonal.ui.theme.SistemaBienestarPersonalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SistemaBienestarPersonalTheme {
                NavGraph()
            }
        }
    }
}
