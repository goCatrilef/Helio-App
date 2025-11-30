package com.example.helioandes.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TituloTexto(
    titulo: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = titulo,
        style = MaterialTheme.typography.headlineLarge,
        color = color,
        modifier = modifier
    )
}