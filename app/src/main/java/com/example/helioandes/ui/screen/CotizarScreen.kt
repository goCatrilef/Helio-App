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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.ui.components.ServiceCard
import com.example.helioandes.ui.components.TituloTexto

@Composable
fun CotizarScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(1) }
    var consumoMensual by remember { mutableStateOf("") }
    var tipoVivienda by remember { mutableStateOf("") }
    var presupuesto by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }

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
            CategoryChip("Cotización Solar")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("Obtén una cotización personalizada para tu sistema solar")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            TituloTexto(
                titulo = "Datos de tu Hogar",
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            CampoTexto(
                valor = consumoMensual,
                onValorCambio = { consumoMensual = it },
                etiqueta = "Consumo mensual (kWh)"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            CampoTexto(
                valor = tipoVivienda,
                onValorCambio = { tipoVivienda = it },
                etiqueta = "Tipo de vivienda"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            CampoTexto(
                valor = presupuesto,
                onValorCambio = { presupuesto = it },
                etiqueta = "Presupuesto estimado"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            CampoTexto(
                valor = ubicacion,
                onValorCambio = { ubicacion = it },
                etiqueta = "Ubicación"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Servicios Incluidos",
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
                    icon = Icons.Default.Home,
                    title = "Visita Técnica",
                    subtitle = "Evaluación gratuita"
                )
                ServiceCard(
                    icon = Icons.Default.Build,
                    title = "Diseño 3D",
                    subtitle = "Personalizado"
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ServiceCard(
                    icon = Icons.Default.Star,
                    title = "Garantía 25 años",
                    subtitle = "Respaldado"
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Boton(
                    texto = "Solicitar Cotización",
                    onClickAccion = {
                        // Agregar la lógica para procesar la cotización
                    }
                )
            }
        }
    }
}