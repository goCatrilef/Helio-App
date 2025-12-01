package com.example.helioandes.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CampoTexto
import com.example.helioandes.ui.components.TituloTexto
import com.example.helioandes.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val viewModel: LoginViewModel = viewModel()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TituloTexto(
                titulo = "Bienvenido",
                color = Color(0xFF008080),
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = usuario,
                onValorCambio = { usuario = it },
                etiqueta = "Usuario"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = contrasena,
                onValorCambio = { contrasena = it },
                etiqueta = "Contraseña",
                visualTransformation = PasswordVisualTransformation()
            )

            if (mensaje.isNotEmpty()) {
                Text(
                    text = mensaje,
                    color = if (mensaje == "Inicio de sesión exitoso") Color.Green else Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Boton(
                texto = "Iniciar Sesión",
                onClickAccion = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val loginExitoso = viewModel.login(usuario, contrasena)

                        withContext(Dispatchers.Main){
                            if (loginExitoso){
                                mensaje = "Inicio de sesión exitoso"
                                navController.navigate("home")

                            }else{
                                mensaje = "Usuario o Contraseña Inválido"
                            }
                        }
                    }

                }
            )
        }
    }

}