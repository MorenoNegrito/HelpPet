package com.example.koltin.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.koltin.R
import com.example.koltin.navigation.Screen
import com.example.kotlin.viewmodel.DesarrolladorViewModel

private val AzulPrimario = Color(0xFF1976D2)

// Clase simple para las opciones del menú
data class OpcionMenu(
    val titulo: String,
    val icono: ImageVector,
    val ruta: String
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
        OpcionMenu("Mascotas", Icons.Default.Pets, Screen.Mascotas.route),
        OpcionMenu("Reservar Hora", Icons.Default.CalendarMonth, Screen.ReservarHora.route),
        OpcionMenu("Resumen", Icons.Default.Assessment, Screen.Resumen.route),
        OpcionMenu("Usuarios", Icons.Default.People, Screen.ResumenUsuarios.route),
        OpcionMenu("Mi Perfil", Icons.Default.Person, Screen.profile.route),
        OpcionMenu("Configuración", Icons.Default.Settings, Screen.setting.route)
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = AzulPrimario)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
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
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                // LazyVerticalGrid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(opcionesMenu) { opcion ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clickable { navController.navigate(opcion.ruta) },
                            colors = CardDefaults.cardColors(containerColor = Color.White)
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
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = opcion.titulo,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black // letras negras
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
