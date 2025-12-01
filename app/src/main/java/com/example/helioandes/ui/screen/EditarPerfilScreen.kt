package com.example.helioandes.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.viewmodel.PerfilViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(navController: NavController) {
    val viewModel: PerfilViewModel = viewModel()
    val usuario by viewModel.usuario.collectAsState()
    
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    LaunchedEffect(usuario) {
        usuario?.let {
            nombre = it.nombre
            correo = it.correo
        }
    }

    LaunchedEffect(Unit) {
        viewModel.cargarUsuarioActual()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Editar Perfil",
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
            CategoryChip("Editar Información")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("Actualiza tu información personal")
            
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(100.dp),
                        tint = Color(0xFF00A896)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    TituloTexto(
                        titulo = "Información Personal",
                        color = Color(0xFF1A1A2E)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    CampoTexto(
                        valor = nombre,
                        onValorCambio = { nombre = it },
                        etiqueta = "Nombre completo"
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    CampoTexto(
                        valor = correo,
                        onValorCambio = { correo = it },
                        etiqueta = "Correo electrónico"
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    TituloTexto(
                        titulo = "Cambiar Contraseña",
                        color = Color(0xFF1A1A2E)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Deja en blanco si no deseas cambiar la contraseña",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    CampoTexto(
                        valor = contrasena,
                        onValorCambio = { contrasena = it },
                        etiqueta = "Nueva contraseña"
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    CampoTexto(
                        valor = confirmarContrasena,
                        onValorCambio = { confirmarContrasena = it },
                        etiqueta = "Confirmar nueva contraseña"
                    )
                    
                    if (mensaje.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = mensaje,
                            color = if (mensaje.contains("exitosamente")) Color.Green else Color.Red,
                            fontSize = 14.sp
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Boton(
                        texto = "Guardar Cambios",
                        onClickAccion = {
                            if (nombre.isBlank() || correo.isBlank()) {
                                mensaje = "Por favor completa todos los campos obligatorios"
                                return@Boton
                            }
                            
                            if (contrasena.isNotEmpty() && contrasena != confirmarContrasena) {
                                mensaje = "Las contraseñas no coinciden"
                                return@Boton
                            }
                            
                            usuario?.let { usuarioActual ->
                                val usuarioActualizado = usuarioActual.copy(
                                    nombre = nombre,
                                    correo = correo,
                                    contrasena = if (contrasena.isNotEmpty()) contrasena else usuarioActual.contrasena
                                )
                                
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        viewModel.actualizarUsuario(usuarioActualizado)
                                        withContext(Dispatchers.Main) {
                                            mensaje = "Perfil actualizado exitosamente"
                                        }
                                    } catch (e: Exception) {
                                        withContext(Dispatchers.Main) {
                                            mensaje = "Error al actualizar perfil"
                                        }
                                    }
                                }
                            }
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Boton(
                        texto = "Cancelar",
                        onClickAccion = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}