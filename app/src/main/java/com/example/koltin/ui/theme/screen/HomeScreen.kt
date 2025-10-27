package com.example.koltin.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.koltin.R
import com.example.koltin.navigation.Screen
import com.example.kotlin.viewmodel.DesarrolladorViewModel
import kotlin.contracts.contract

private val AzulPrimario = Color(0xFF1976D2)

// Clase simple para las opciones del menú
data class OpcionMenu(
    val titulo: String,
    val icono: ImageVector,
    val ruta: String,
    val descripccion: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithNavBar(
    navController: NavController,
    viewModel: DesarrolladorViewModel
) {
    // Obtener el estado del viewModel
    //Transforamos el stateflow del viewmodel en un estado observable de compose
    //Si cambia el nombre o correo la interfaz se actualiza automaticamente
    val estado by viewModel.estado.collectAsState()

    // Lista de opciones del menú
    val opcionesMenu = listOf(
        OpcionMenu("Mascotas", Icons.Default.Pets, Screen.Mascotas.route, "Ver y administrar tus mascotas"),
        OpcionMenu("Reservar Hora", Icons.Default.CalendarMonth, Screen.ReservarHora.route, "Agendar citas para tus mascotas"),
        OpcionMenu("Resumen", Icons.Default.Assessment, Screen.Resumen.route, "Ver estadísticas y reportes"),
        OpcionMenu("Usuarios", Icons.Default.People, Screen.ResumenUsuarios.route, "Gestionar usuarios del sistema"),
        OpcionMenu("Mi Perfil", Icons.Default.Person, Screen.profile.route, "Actualizar tu información personal"),
        OpcionMenu("Configuración", Icons.Default.Settings, Screen.setting.route, "Ajustes de la aplicación")
    )

    //Scafold que es un contenedor jetpack que nos da estructura a la pantalla
    Scaffold(
        topBar = { /* tu TopAppBar */ }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.fondo_pantalla),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Contenido encima de la imagen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Tarjeta de bienvenida
                // Tarjeta de bienvenida mejorada
                // Tarjeta de bienvenida azul
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(8.dp, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)), // Azul sólido
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = estado.nombre.ifBlank { "Usuario" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                        Text(
                            text = estado.correo,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }


                // Grid de opciones
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 columnas
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp), // más separación horizontal
                    verticalArrangement = Arrangement.spacedBy(24.dp),   // más separación vertical
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(opcionesMenu) { opcion ->
                        // Tarjeta de opción
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp) // más alta para ocupar más espacio
                                .clickable { navController.navigate(opcion.ruta) },
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(16.dp), // bordes redondeados
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = opcion.icono,
                                    contentDescription = opcion.titulo,
                                    tint = AzulPrimario,
                                    modifier = Modifier.size(48.dp) // icono más grande
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = opcion.titulo,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = opcion.descripccion, //
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    maxLines = 2,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}