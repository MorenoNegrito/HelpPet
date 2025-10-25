package com.example.koltin.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koltin.navigation.Screen
import com.example.kotlin.viewmodel.DesarrolladorViewModel
import kotlinx.coroutines.delay
private val Azul = Color(0xFF1976D2)

@Composable
fun RegistroDesarrolladorScreen(
    navController: NavController,
    viewModel: DesarrolladorViewModel
) {
    val estado by viewModel.estado.collectAsState()

    // Detectar sesión activa solo al iniciar (Unit = una sola vez)
    LaunchedEffect(Unit) {
        // Pequeña espera para que cargue el estado inicial
        kotlinx.coroutines.delay(300)
        if (estado.nombre.isNotBlank() && estado.correo.isNotBlank()) {
            println("✅ Sesión detectada: ${estado.nombre}")
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Registro.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro Desarrollador",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Azul
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedTextField(
                value = estado.nombre,
                onValueChange = viewModel::onNombreChange,
                label = { Text("Nombre completo") },
                isError = estado.errores.nombre != null,
                supportingText = {
                    estado.errores.nombre?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Azul,
                    focusedLabelColor = Azul,
                    cursorColor = Azul
                )
            )

            OutlinedTextField(
                value = estado.correo,
                onValueChange = viewModel::onCorreoChange,
                label = { Text("Correo electrónico") },
                isError = estado.errores.correo != null,
                supportingText = {
                    estado.errores.correo?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Azul,
                    focusedLabelColor = Azul,
                    cursorColor = Azul
                )
            )

            OutlinedTextField(
                value = estado.clave,
                onValueChange = viewModel::onClaveChange,
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                isError = estado.errores.clave != null,
                supportingText = {
                    estado.errores.clave?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Azul,
                    focusedLabelColor = Azul,
                    cursorColor = Azul
                )
            )

            OutlinedTextField(
                value = estado.direccion,
                onValueChange = viewModel::onDireccionChange,
                label = { Text("Dirección") },
                isError = estado.errores.direccion != null,
                supportingText = {
                    estado.errores.direccion?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Azul,
                    focusedLabelColor = Azul,
                    cursorColor = Azul
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Checkbox(
                checked = estado.aceptaTerminos,
                onCheckedChange = viewModel::onAceptarTerminosChange,
                colors = CheckboxDefaults.colors(checkedColor = Azul)
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto los términos y condiciones")
        }

        // BOTÓN CORREGIDO: Solo navega cuando el guardado termine
        Button(
            onClick = {
                if (viewModel.validarFormulario()) {
                    viewModel.registrarDesarrollador()
                    // La navegación se hace automáticamente por el LaunchedEffect
                    // cuando detecta que hay nombre y correo guardados
                }
            },
            enabled = !estado.cargando,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Azul)
        ) {
            if (estado.cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text("Registrar Desarrollador", fontWeight = FontWeight.Bold)
            }
        }

        estado.errorGeneral?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}