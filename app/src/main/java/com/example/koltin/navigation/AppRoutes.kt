package com.example.koltin.navigation

//Cada pantalla tiene su propio ruta
sealed class Screen(val route: String) {
    //Cada pantalla es una constancia unico
    data object Home : Screen("Home_page")
    data object profile : Screen("Profile_page")
    data object setting : Screen("setting_Page")
    data object Registro : Screen("Registro_page")
    data object Resumen : Screen("Resumen_page")
    data object Mascotas : Screen("Mascotas_page")
    data object ReservarHora : Screen("ReservarHora_page")

    data object ResumenUsuarios : Screen("Resumen_usuarios")

    data class Detail(val itemId: String) : Screen("detail_page/{itemId}") {
        fun buildRoute(): String {
            return route.replace("{itemId}", itemId)
        }
    }
}