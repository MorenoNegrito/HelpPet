package com.example.koltin.ui.theme.screen

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


private val AzulPrimario = Color(0xFF1976D2)

data class Mascota(
    val nombre: String,
    val tipo: String,
    val edad : Int,
    val raza : String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MascotasScreen(navController: NavController){
    //Creacion de datos falsos
    val mascotas = remember {
        listOf(
            Mascota("Panqueque ","Perro", 7, "Quiltrico"),
            Mascota("Motor ","Gato", 1, "Atigrado"),
            Mascota("Negrito ","Gato", 2, "Tuxedo"),
            Mascota("Panqueque ","Gato", 1, "Calico"),
            Mascota("Hawas ice", "Perfume", 4, "Lataffa")

        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis mascotitas") },
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
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
                onClick = {/*Aqui agregamos mascota*/},
                containerColor = AzulPrimario,
            ) {
                Icon(Icons.Default.Add , "Agregar mascota", tint = Color.White)

            }
        }
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues ),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)


        ){
            items(mascotas) { mascota ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)

                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if(mascota.tipo == "Perro")
                                Icons.Default.Pets
                            else
                                Icons.Default.FavoriteBorder,
                            contentDescription = mascota.tipo,
                            modifier = Modifier.size(48.dp),
                            tint = AzulPrimario
                        )

                        Spacer(modifier = Modifier.width(16.dp))
                        Column (modifier = Modifier.weight(1f)){
                            Text(
                                text = mascota.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${mascota.edad} años",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "Ver detalles ",
                            tint = Color.Gray
                        )

                    }
                }
            }
        }
    }
}