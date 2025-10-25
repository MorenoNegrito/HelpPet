package com.example.kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.koltin.data.local.DesarrolladorPreferences
import com.example.koltin.data.model.DesarrolladorUiState
import com.example.koltin.data.model.DesarrolladorErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DesarrolladorViewModel(application: Application) : AndroidViewModel(application) {

    private val _estado = MutableStateFlow(DesarrolladorUiState())
    val estado: StateFlow<DesarrolladorUiState> = _estado

    // DataStore
    private val prefs = DesarrolladorPreferences(application.applicationContext)

    init {
        // Cargar datos guardados al iniciar el ViewModel
        cargarSesion()
    }

    // --- Cargar sesión guardada ---
    private fun cargarSesion() {
        viewModelScope.launch {
            prefs.obtenerDesarrollador.collect { (nombre, correo) ->
                if (!nombre.isNullOrBlank() && !correo.isNullOrBlank()) {
                    _estado.update {
                        it.copy(
                            nombre = nombre,
                            correo = correo
                        )
                    }
                }
            }
        }
    }

    // --- Funciones de actualización de campos ---
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // --- Validación del formulario ---
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value

        // Validar que acepte términos
        if (!estadoActual.aceptaTerminos) {
            _estado.update {
                it.copy(errorGeneral = "Debes aceptar los términos y condiciones")
            }
            return false
        }

        val errores = DesarrolladorErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = when {
                estadoActual.correo.isBlank() -> "Campo obligatorio"
                !estadoActual.correo.contains("@") -> "Correo inválido"
                else -> null
            },
            clave = when {
                estadoActual.clave.isBlank() -> "Campo obligatorio"
                estadoActual.clave.length < 6 -> "Debe tener al menos 6 caracteres"
                else -> null
            },
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores, errorGeneral = null) }

        return !hayErrores
    }

    // --- Registrar desarrollador ---
    fun registrarDesarrollador() {
        viewModelScope.launch {
            _estado.update { it.copy(cargando = true) }

            try {
                val estadoActual = _estado.value

                // Guardar datos en DataStore
                prefs.guardarDesarrollador(
                    nombre = estadoActual.nombre,
                    correo = estadoActual.correo
                )

                println("✅ Desarrollador guardado: ${estadoActual.nombre}")

                _estado.update {
                    it.copy(
                        cargando = false,
                        errorGeneral = null
                    )
                }
            } catch (e: Exception) {
                _estado.update {
                    it.copy(
                        cargando = false,
                        errorGeneral = "Error al guardar: ${e.message}"
                    )
                }
            }
        }
    }

    // --- Cerrar sesión ---
    fun cerrarSesion() {
        viewModelScope.launch {
            prefs.cerrarSesion()
            _estado.update { DesarrolladorUiState() }
            println("🔒 Sesión cerrada")
        }
    }

    // --- Verifica si hay sesión activa ---
    fun tieneSesionActiva(): Boolean {
        return _estado.value.nombre.isNotBlank() && _estado.value.correo.isNotBlank()
    }
}