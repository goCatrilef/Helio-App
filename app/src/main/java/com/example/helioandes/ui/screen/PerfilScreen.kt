package com.example.helioandes.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.helioandes.model.Usuario
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.ui.components.ServiceCard
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(4) } 
    val viewModel: PerfilViewModel = viewModel()
    val usuario by viewModel.usuario.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.cargarUsuarioActual()
    }

    Scaffold(
        topBar = { HeaderApp() },
        bottomBar = {
            BarraNavegacion(
                selectedItem = selectedBottomItem,
                onItemSelected = { index ->
                    selectedBottomItem = index
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("cotizar")
                        2 -> navController.navigate("contacto")
                        3 -> navController.navigate("carrito")
                        4 -> navController.navigate("perfil")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CategoryChip("Mi Perfil")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("Gestiona tu información personal")
            
            Spacer(modifier = Modifier.height(24.dp))

           
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    Box {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Avatar",
                            modifier = Modifier.size(100.dp),
                            tint = Color(0xFF00A896)
                        )
                        
                        IconButton(
                            onClick = { 
                                navController.navigate("editar_perfil")
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(
                                    Color(0xFF00A896),
                                    CircleShape
                                )
                                .size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Editar perfil",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    usuario?.let { user ->
                        Text(
                            text = user.nombre,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = user.correo,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    } ?: run {
                        Text(
                            text = "Cargando...",
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            
            TituloTexto(
                titulo = "Información de la Cuenta",
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            usuario?.let { user ->
                InfoCard(
                    titulo = "Nombre",
                    valor = user.nombre
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                InfoCard(
                    titulo = "Correo Electrónico",
                    valor = user.correo
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                InfoCard(
                    titulo = "Usuario desde",
                    valor = "Noviembre 2024" 
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
      
            Text(
                text = "Actividad Reciente",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ServiceCard(
                    icon = Icons.Default.AccountCircle,
                    title = "Cotizaciones",
                    subtitle = "3 solicitudes",
                    modifier = Modifier.weight(1f)
                )
                ServiceCard(
                    icon = Icons.Default.AccountCircle,
                    title = "Mensajes",
                    subtitle = "2 enviados",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun InfoCard(
    titulo: String,
    valor: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1A1A2E)
            )
            
            Text(
                text = valor,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}