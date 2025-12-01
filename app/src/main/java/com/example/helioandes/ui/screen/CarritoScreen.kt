package com.example.helioandes.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.helioandes.model.Producto
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.viewmodel.ProductoViewModel

data class ProductoCarrito(
    val producto: Producto,
    val cantidad: Int
)

@Composable
fun CarritoScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(3) }
    val viewModel: ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()
    
    // Simulación de productos en el carrito (en una app real esto vendría del ViewModel)
    val productosCarrito = remember {
        mutableStateOf(
            listOf(
                ProductoCarrito(
                    Producto(1, "Panel Solar 450w", "Ideal para climas soleados", 189990, "logo.png"),
                    2
                ),
                ProductoCarrito(
                    Producto(2, "Inversor 5kW Hibrido", "Compatible con baterias", 899990, "logo.png"),
                    1
                )
            )
        )
    }

    val total = productosCarrito.value.sumOf { it.producto.precio * it.cantidad }

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
                        4 -> navController.navigate("perfil")
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
        ) {
            CategoryChip("Mi Carrito")
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DescriptionText("Revisa y confirma tu pedido")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            if (productosCarrito.value.isEmpty()) {
                EmptyCartMessage()
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(productosCarrito.value) { productoCarrito ->
                        ProductoCarritoItem(
                            productoCarrito = productoCarrito,
                            onCantidadChange = { nuevaCantidad ->
                                val nuevaLista = productosCarrito.value.toMutableList()
                                val index = nuevaLista.indexOfFirst { it.producto.id == productoCarrito.producto.id }
                                if (index != -1) {
                                    if (nuevaCantidad > 0) {
                                        nuevaLista[index] = productoCarrito.copy(cantidad = nuevaCantidad)
                                    } else {
                                        nuevaLista.removeAt(index)
                                    }
                                    productosCarrito.value = nuevaLista
                                }
                            }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Resumen del Pedido",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Subtotal:", fontSize = 16.sp)
                            Text("$${String.format("%,d", total)}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Envío:", fontSize = 16.sp)
                            Text("Gratis", fontSize = 16.sp, color = Color(0xFF00A896))
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("$${String.format("%,d", total)}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00A896))
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Boton(
                        texto = "Proceder al Pago",
                        onClickAccion = {
                            // Lógica para proceder al pago
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoCarritoItem(
    productoCarrito: ProductoCarrito,
    onCantidadChange: (Int) -> Unit
) {
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = productoCarrito.producto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = productoCarrito.producto.descripcion,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "$${String.format("%,d", productoCarrito.producto.precio)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00A896)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { 
                        if (productoCarrito.cantidad > 1) {
                            onCantidadChange(productoCarrito.cantidad - 1)
                        }
                    }
                ) {
                    Text(
                        text = "−", // Signo menos matemático
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00A896)
                    )
                }
                
                Text(
                    text = "${productoCarrito.cantidad}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                
                IconButton(
                    onClick = { onCantidadChange(productoCarrito.cantidad + 1) }
                ) {
                    Text(
                        text = "+", // Signo más
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00A896)
                    )
                }
                
                IconButton(
                    onClick = { onCantidadChange(0) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}

@Composable
fun EmptyCartMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = "Carrito vacío",
                modifier = Modifier.size(80.dp),
                tint = Color.Gray
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Tu carrito está vacío",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Explora nuestros productos y añade algunos al carrito",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}