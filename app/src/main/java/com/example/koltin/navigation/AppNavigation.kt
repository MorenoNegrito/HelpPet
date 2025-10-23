package com.example.koltin.navigation


import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.koltin.ui.theme.screen.HomeScreenWithNavBar
import com.example.koltin.ui.theme.screen.MascotasScreen
import com.example.koltin.ui.theme.screen.ProfileScreen
import com.example.koltin.ui.theme.screen.RegistroScreen
import com.example.koltin.ui.theme.screen.ReservarHoraScreen
import com.example.koltin.ui.theme.screen.ResumenScreen
import com.example.koltin.ui.theme.screen.ResumenUsuarios
import com.example.koltin.ui.theme.screen.SettingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreenWithNavBar(navController)
        }

        composable(Screen.Mascotas.route) {
            MascotasScreen(navController)
        }

        composable(Screen.ReservarHora.route) {
            ReservarHoraScreen(navController)
        }

        composable(Screen.profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.setting.route) {
            SettingScreen(navController)
        }

        composable(Screen.Registro.route) {
            RegistroScreen(navController)
        }

        composable(Screen.Resumen.route) {
            ResumenScreen(navController)

        }

        composable (Screen.ResumenUsuarios.route){
            ResumenUsuarios(navController)
        }
    }
}