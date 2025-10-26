package com.example.koltin.ui.theme.screen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

private val AzulPrimario = Color(0xFF1976D2)

data class ReservaHora(
    val mascota: String,
    val propietario: String,
    val fecha: String,
    val hora: String,
    val servicio: String,
    val estado: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservarHoraScreen(navController: NavController) {
    // Datos de ejemplo
    val reservas = remember {
        listOf(
            ReservaHora("Luna", "Juan Pérez", "25/10/2025", "10:00", "Consulta General", "Confirmada"),
            ReservaHora("Michi", "María López", "25/10/2025", "11:30", "Vacunación", "Pendiente"),
            ReservaHora("Rocky", "Carlos García", "26/10/2025", "09:00", "Control", "Confirmada")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reservas de hora") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulPrimario,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Aquí agregar reserva */ },
                containerColor = AzulPrimario
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.White)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reservas) { reserva ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)

                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = reserva.mascota,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )

                        Text(text = "Propietario: ${reserva.propietario}",
                            color = Color.Black)
                        Text(text = "Fecha: ${reserva.fecha} - ${reserva.hora}",
                            color = Color.Green)
                        Text(text = "Servicio: ${reserva.servicio}",
                            color = Color.Magenta)


                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Estado: ${reserva.estado}",
                            color = when (reserva.estado) {
                                "Confirmada" -> Color(0xFF4CAF50)
                                "Pendiente" -> Color(0xFFFF9800)
                                else -> Color(0xFFD32F2F)
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
