package com.example.koltin.ui.theme.screen

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

// Clase simple para las reservas de hora
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
    //  (datos falsos)
    val reservas = remember {
        listOf(
            ReservaHora("Luna", "Juan Pérez", "25/10/2025", "10:00", "Consulta General", "Confirmada"),
            ReservaHora("Michi", "María López", "25/10/2025", "11:30", "Vacunación", "Pendiente"),
            ReservaHora("Rocky", "Carlos García", "26/10/2025", "09:00", "Control", "Confirmada"),
            ReservaHora("Nala", "Ana Martínez", "26/10/2025", "14:00", "Baño y Peluquería", "Pendiente"),
            ReservaHora("Max", "Pedro Sánchez", "27/10/2025", "10:30", "Cirugía", "Confirmada"),
            ReservaHora("Toby", "Laura Ruiz", "27/10/2025", "15:00", "Consulta General", "Cancelada")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Horas") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Volver")
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
                onClick = { /* Aquí iría agregar nueva reserva */ },
                containerColor = AzulPrimario
            ) {
                Icon(Icons.Default.Add, "Agregar reserva", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tarjeta de resumen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${reservas.count { it.estado == "Confirmada" }}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        )
                        Text("Confirmadas", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${reservas.count { it.estado == "Pendiente" }}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9800)
                        )
                        Text("Pendientes", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${reservas.size}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = AzulPrimario
                        )
                        Text("Total", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            // Lista de reservas
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(reservas) { reserva ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Encabezado con mascota y estado
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Pets,
                                        contentDescription = null,
                                        tint = AzulPrimario,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = reserva.mascota,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // Badge de estado
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = when (reserva.estado) {
                                            "Confirmada" -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                                            "Pendiente" -> Color(0xFFFF9800).copy(alpha = 0.2f)
                                            else -> Color(0xFFD32F2F).copy(alpha = 0.2f)
                                        }
                                    )
                                ) {
                                    Text(
                                        text = reserva.estado,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        color = when (reserva.estado) {
                                            "Confirmada" -> Color(0xFF4CAF50)
                                            "Pendiente" -> Color(0xFFFF9800)
                                            else -> Color(0xFFD32F2F)
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Propietario
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = reserva.propietario,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            // Fecha y hora
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${reserva.fecha} - ${reserva.hora}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            // Servicio
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.MedicalServices,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = reserva.servicio,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }

                            // Botones de acción (solo para Pendientes)
                            if (reserva.estado == "Pendiente") {
                                Spacer(modifier = Modifier.height(12.dp))
                                Divider()
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    TextButton(
                                        onClick = { /* Confirmar */ }
                                    ) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color(0xFF4CAF50)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Confirmar", color = Color(0xFF4CAF50))
                                    }

                                    TextButton(
                                        onClick = { /* Cancelar */ }
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = null,
                                            tint = Color(0xFFD32F2F)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Cancelar", color = Color(0xFFD32F2F))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}