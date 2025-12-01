package com.example.helioandes.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.ServiceCard
import com.example.helioandes.ui.components.HeaderApp


@Composable
fun HomeScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { HeaderApp() },
        bottomBar = {
            BarraNavegacion(
                selectedItem = selectedBottomItem,
                onItemSelected = { selectedBottomItem = it }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(20.dp)
        ) {

            CategoryChip("Energía Solar")

            Spacer(modifier = Modifier.height(12.dp))

            DescriptionText("Dimensiona tu sistema y conoce el costo estimado en minutos.")

            Spacer(modifier = Modifier.height(20.dp))


            Boton("Cotizar") { }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Nuestros Productos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cards de Productos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ServiceCard(
                    icon = Icons.Default.Info,
                    title = "Estudio Energético",
                    subtitle = "Análisis a medida"
                )
                ServiceCard(
                    icon = Icons.Default.Check,
                    title = "Instalación Certificada",
                    subtitle = "Profesional 360°"
                )
            }
        }
    }
}







