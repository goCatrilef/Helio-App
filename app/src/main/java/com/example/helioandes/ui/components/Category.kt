package com.example.helioandes.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//Etiqueta de ubicacion
@Composable
fun CategoryChip(text: String) {
    Surface(
        color = Color(0xFF006064),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color( 0xFFE0F7FA),//0xFFE0F7FA
            style = MaterialTheme.typography.labelMedium
        )
    }
}