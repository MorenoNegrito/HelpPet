package com.example.koltin.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("desarrollador_prefs")

class DesarrolladorPreferences(private val context: Context) {

    companion object {
        val NOMBRE = stringPreferencesKey("nombre")
        val CORREO = stringPreferencesKey("correo")
    }

    // Guardar datos del desarrollador
    suspend fun guardarDesarrollador(nombre: String, correo: String) {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE] = nombre
            prefs[CORREO] = correo
        }
    }

    // Leer datos guardados
    val obtenerDesarrollador: Flow<Pair<String?, String?>> = context.dataStore.data.map { prefs ->
        Pair(prefs[NOMBRE], prefs[CORREO])
    }

    // Borrar sesión (logout)
    suspend fun cerrarSesion() {
        context.dataStore.edit { it.clear() }
    }
}
