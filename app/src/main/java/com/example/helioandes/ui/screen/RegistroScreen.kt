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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.viewmodel.RegistroViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val viewModel: RegistroViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Crear Cuenta",
                        color = Color(0xFF008080),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF008080)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5)
                )
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
            CategoryChip("Nuevo Usuario")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("Únete a HelioAndes y comienza tu proyecto solar")
            
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    TituloTexto(
                        titulo = "Crear Cuenta",
                        color = Color(0xFF008080),
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Completa la información para registrarte",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    CampoTexto(
                        valor = nombre,
                        onValorCambio = { 
                            nombre = it
                            mensaje = "" 
                        },
                        etiqueta = "Nombre completo"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = correo,
                        onValorCambio = { 
                            correo = it.lowercase().trim()
                            mensaje = ""
                        },
                        etiqueta = "Correo electrónico"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = contrasena,
                        onValorCambio = { 
                            contrasena = it
                            mensaje = ""
                        },
                        etiqueta = "Contraseña",
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CampoTexto(
                        valor = confirmarContrasena,
                        onValorCambio = { 
                            confirmarContrasena = it
                            mensaje = ""
                        },
                        etiqueta = "Confirmar contraseña",
                        visualTransformation = PasswordVisualTransformation()
                    )

                    if (mensaje.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = mensaje,
                            color = when {
                                mensaje.contains("exitoso") -> Color.Green
                                mensaje.contains("existe") -> Color(0xFFFF9800)
                                else -> Color.Red
                            },
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Boton(
                        texto = if (isLoading) "Registrando..." else "Crear Cuenta",
                        onClickAccion = {
                            when {
                                nombre.isBlank() -> mensaje = "Por favor ingresa tu nombre"
                                correo.isBlank() -> mensaje = "Por favor ingresa tu correo"
                                !correo.contains("@") -> mensaje = "Ingresa un correo válido"
                                contrasena.isBlank() -> mensaje = "Por favor ingresa una contraseña"
                                contrasena.length < 6 -> mensaje = "La contraseña debe tener al menos 6 caracteres"
                                contrasena != confirmarContrasena -> mensaje = "Las contraseñas no coinciden"
                                else -> {
                                    isLoading = true
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val registroExitoso = viewModel.registrarUsuario(
                                            nombre = nombre.trim(),
                                            correo = correo,
                                            contrasena = contrasena
                                        )

                                        withContext(Dispatchers.Main) {
                                            isLoading = false
                                            if (registroExitoso) {
                                                mensaje = "Registro exitoso. Redirigiendo..."
                                                CoroutineScope(Dispatchers.Main).launch {
                                                    kotlinx.coroutines.delay(1500)
                                                    navController.navigate("login") {
                                                        popUpTo("registro") { inclusive = true }
                                                    }
                                                }
                                            } else {
                                                mensaje = "Este correo ya está registrado"
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "¿Ya tienes cuenta?",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Boton(
                        texto = "Iniciar Sesión",
                        onClickAccion = {
                            navController.navigate("login") {
                                popUpTo("registro") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}