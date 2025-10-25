package com.example.koltin.data.model

data class DesarrolladorUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = "",
    val aceptaTerminos: Boolean = false,
    val cargando: Boolean = false,
    val errorGeneral: String? = null,
    val errores: DesarrolladorErrores = DesarrolladorErrores()
)