package com.example.helioandes.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TituloTexto(titulo : String){
    Text(
        text = titulo,
        style = MaterialTheme.typography.headlineLarge,
    )
}