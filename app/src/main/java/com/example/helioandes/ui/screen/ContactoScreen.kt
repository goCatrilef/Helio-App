package com.example.helioandes.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helioandes.model.FormularioContacto
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.viewmodel.ContactoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ContactoScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(2) }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var tipoConsulta by remember { mutableStateOf("") }
    var asunto by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var mensajeEstado by remember { mutableStateOf("") }
    
    val viewModel: ContactoViewModel = viewModel()

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
        ){
            CategoryChip("Contáctanos")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("¿Tienes dudas? Estamos aquí para ayudarte con tu proyecto solar")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ){
                    TituloTexto(
                        titulo = "Formulario de Contacto",
                        color = Color(0xFF1A1A2E)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Completa el formulario y te contactaremos",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    CampoTexto(
                        valor = nombre,
                        onValorCambio = { nombre = it },
                        etiqueta = "Nombre completo"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = email,
                        onValorCambio = { email = it },
                        etiqueta = "Correo electrónico"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = telefono,
                        onValorCambio = { telefono = it },
                        etiqueta = "Teléfono"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = tipoConsulta,
                        onValorCambio = { tipoConsulta = it },
                        etiqueta = "Tipo de consulta"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = asunto,
                        onValorCambio = { asunto = it },
                        etiqueta = "Asunto"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = mensaje,
                        onValorCambio = { mensaje = it },
                        etiqueta = "Mensaje"
                    )

                    if (mensajeEstado.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = mensajeEstado,
                            color = if (mensajeEstado.contains("exitoso")) Color.Green else Color.Red,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Boton(
                        texto = "Enviar Mensaje",
                        onClickAccion = {
                            if (nombre.isNotEmpty() && email.isNotEmpty() && mensaje.isNotEmpty()) {
                                val formulario = FormularioContacto(
                                    nombre = nombre,
                                    email = email,
                                    telefono = telefono,
                                    tipoConsulta = tipoConsulta,
                                    asunto = asunto,
                                    mensaje = mensaje
                                )
                                
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        viewModel.guardarFormularioContacto(formulario)
                                        withContext(Dispatchers.Main) {
                                            mensajeEstado = "Mensaje enviado exitosamente"
                                            nombre = ""
                                            email = ""
                                            telefono = ""
                                            tipoConsulta = ""
                                            asunto = ""
                                            mensaje = ""
                                        }
                                    } catch (e: Exception) {
                                        withContext(Dispatchers.Main) {
                                            mensajeEstado = "Error al enviar mensaje"
                                        }
                                    }
                                }
                            } else {
                                mensajeEstado = "Por favor completa los campos obligatorios"
                            }
                        }
                    )
                }
            }
        }
    }
}
