package com.example.koltin.ui.theme.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun MascotasScreen(navController: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gestión de Mascotas", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.navigateUp() }) {
            Text("Volver")
        }
    }
}