package com.example.helioandes.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Color.Gray
    )
}