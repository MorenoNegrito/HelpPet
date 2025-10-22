package com.example.koltin.ui.theme.componentes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.koltin.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Text("🏠") },
            label = { Text("Inicio") },
            selected = true,
            onClick = { navController.navigate(Screen.Home.route) }
        )

        NavigationBarItem(
            icon = { Text("🐕") },
            label = { Text("Mascotas") },
            selected = false,
            onClick = { navController.navigate(Screen.Mascotas.route) }
        )

        NavigationBarItem(
            icon = { Text("⏰") },
            label = { Text("Horas") },
            selected = false,
            onClick = { navController.navigate(Screen.ReservarHora.route) }
        )

        NavigationBarItem(
            icon = { Text("🚪") },
            label = { Text("Salir") },
            selected = false,
            onClick = { navController.navigate(Screen.Registro.route) }
        )
    }
}