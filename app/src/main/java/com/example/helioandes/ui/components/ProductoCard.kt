package com.example.helioandes.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helioandes.R
import com.example.helioandes.model.Producto

@Composable
fun ProductoCard(
    producto: Producto,
    onAddToCart: (Producto) -> Unit = {}
) {
    val context = LocalContext.current
    val imageResource = obtieneImagenProducto(context, producto.imagen)
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
    
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Imagen de ${producto.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )
            

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = producto.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "$${String.format("%,d", producto.precio)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00A896)
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            

            IconButton(
                onClick = { onAddToCart(producto) }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Agregar al carrito",
                    tint = Color(0xFF00A896),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

private fun obtieneImagenProducto(context: Context, imagen: String?): Int {
    val nombre = imagen?.replace(".png", "") ?: "logo"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)
    return if (resourceId == 0) R.drawable.logo else resourceId
}