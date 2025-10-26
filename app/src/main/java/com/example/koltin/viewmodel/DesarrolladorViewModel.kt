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


//Se hereda de androiwviewmoel para ocupar aplication context y datastore
class DesarrolladorViewModel(application: Application) : AndroidViewModel(application) {

    //Creamos dos variales la que tiene guion bajo es privada solo el viemodel puede cmabiarala
    //Y la estado normal es de solo lectura

    private val _estado = MutableStateFlow(DesarrolladorUiState())
    val estado: StateFlow<DesarrolladorUiState> = _estado

    // creamos una variable para guardas los datos del desarrollador
    private val prefs = DesarrolladorPreferences(application.applicationContext)

    init {
        // Cargar datos guardados al iniciar el ViewModel
        cargarSesion()
    }

    // --- Cargar sesión guardada ---
    private fun cargarSesion() {
        //Ocupamos viewmodelscope para launch para lanzar una corrutina para escuhcar los eventos de datastore
        //Decimos que si el nombre no es nulo y no es blanco lo actualizamos el estado
        //Lo mismo con el correo

        //Se ejectua en segundo plano
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

    // --- Funciones de actualización de campos

    //Actualizar campo de error
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
        //Tomamos el valor actual para analizarlo
        val estadoActual = _estado.value

        // Validar que acepte términos
        //Lo validamos con ! Esto nos dice si el estado actual no valido los terminos
        //Le decimos que debe aceptar terminos
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

    // --- Registrar desarrollador (CON CALLBACK) ---
    //SIGNIFICA QUE ONSUCCES NO DEVEULVE NADA SOLO EJECUTA
    fun registrarDesarrollador(onSuccess: () -> Unit) {
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

                // Actualizar el estado inmediatamente (no esperar al collect)
                _estado.update {
                    it.copy(
                        cargando = false,
                        errorGeneral = null
                    )
                }

                // Llamar al callback de éxito
                onSuccess()

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