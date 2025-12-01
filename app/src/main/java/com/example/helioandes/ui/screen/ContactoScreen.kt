package com.example.helioandes.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto

import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.ui.components.TituloTexto


@Composable
fun ContactoScreen(navController: NavController) {
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
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(24.dp)
                ){
                    // Título
                    TituloTexto("Crear Cuenta")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Únete a la PokeStore",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Campos del formulario
                    CampoTexto(
                        valor = "",
                        onValorCambio = {},
                        etiqueta = "Nombre de usuario"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = "",
                        onValorCambio = {},
                        etiqueta = "Correo electrónico"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = "",
                        onValorCambio = {},
                        etiqueta = "Contraseña"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = "",
                        onValorCambio = {},
                        etiqueta = "Confirmar contraseña"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Boton(
                        texto = "Registrarse",
                        onClickAccion = {}
                    )
                }
            }
        }
    }
}
