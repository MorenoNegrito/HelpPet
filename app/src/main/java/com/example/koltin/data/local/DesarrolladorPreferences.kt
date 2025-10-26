package com.example.koltin.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//CREAMOS UNA PROPIEDAD EXTENDIDA PARA CONTEXT
private val Context.dataStore by preferencesDataStore("desarrollador_prefs")

class DesarrolladorPreferences(private val context: Context) {

    //Es como el static en java pertenece a la clase no a las instancias
    companion object {
        //Creacion de la llave ocn string preferencesKey
        val NOMBRE = stringPreferencesKey("nombre")
        val CORREO = stringPreferencesKey("correo")
    }

    // Guardar datos del desarrollador
    //Funcion que puede pausarse sin bloquar el hilo
    suspend fun guardarDesarrollador(nombre: String, correo: String) {
        //Emite valor cadavez que el data store cambia
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
