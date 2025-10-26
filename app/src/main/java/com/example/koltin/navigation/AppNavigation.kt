package com.example.koltin.navigation

import ResumenScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.koltin.ui.theme.screen.*
import com.example.kotlin.viewmodel.DesarrolladorViewModel

@Composable
fun AppNavigation() {
    //Recordamos el estado de navegacion

    val navController = rememberNavController()
    //Utilizamos instacncai el viewmodel patron mvvm significa que no se destruye al cambiar de pantlla
    //mientas la app siga activa en memoria
    //View model se prepara para persistir entre pantallas
    val desarrolladorViewModel: DesarrolladorViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Registro.route
    ) {
        composable(Screen.Home.route) {
            HomeScreenWithNavBar(navController, desarrolladorViewModel)
        }

        composable(Screen.Mascotas.route) {
            MascotasScreen(navController)
        }

        composable(Screen.ReservarHora.route) {
            ReservarHoraScreen(navController)
        }

        composable(Screen.profile.route) {
            ProfileScreen(navController, desarrolladorViewModel)
        }

        composable(Screen.setting.route) {
            SettingScreen(navController, desarrolladorViewModel)
        }

        composable(Screen.Registro.route) {
            RegistroDesarrolladorScreen(navController, desarrolladorViewModel)
        }

        composable(Screen.Resumen.route) {
            ResumenScreen(navController)
        }

        composable(Screen.ResumenUsuarios.route) {
            ResumenUsuarios(navController)
        }
    }
}