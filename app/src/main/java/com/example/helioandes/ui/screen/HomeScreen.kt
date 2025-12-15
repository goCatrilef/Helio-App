package com.example.helioandes.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.helioandes.R
import com.example.helioandes.model.Producto
import com.example.helioandes.ui.components.BarraNavegacion
import com.example.helioandes.ui.components.Boton
import com.example.helioandes.ui.components.CategoryChip
import com.example.helioandes.ui.components.DescriptionText
import com.example.helioandes.ui.components.ServiceCard
import com.example.helioandes.ui.components.HeaderApp
import com.example.helioandes.ui.components.ProductoCard
import com.example.helioandes.viewmodel.ProductoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf(0) }
    val viewModel : ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit){
        viewModel.cargarProductos()
    }

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

            CategoryChip("Energía Solar")

            Spacer(modifier = Modifier.height(12.dp))

            DescriptionText("Dimensiona tu sistema y conoce el costo estimado en minutos.")

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Nuestros Productos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Productos mejorada
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos) { producto ->
                    ProductoCard(
                        producto = producto,
                        onAddToCart = { 
                            // Aquí podrías agregar lógica futura para el carrito
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Servicios
            Text(
                text = "Nuestros Servicios",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ServiceCard(
                    icon = Icons.Default.Info,
                    title = "Estudio Energético",
                    subtitle = "Análisis a medida",
                    modifier = Modifier.weight(1f)
                )
                ServiceCard(
                    icon = Icons.Default.Check,
                    title = "Instalación Certificada",
                    subtitle = "Profesional 360°",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Boton(
            texto = "Post de API",
            onClickAccion = {
                navController.navigate("post")
            }
        )

    }
}

@Composable
fun ProductoItem(producto: Producto) {
    val context = LocalContext.current
    val imageResource = obtieneImagen(context, producto.imagen)
//aqui
    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        Image(
            //importante para resolver los problemas resources R. , se elige el nombre del package del proyecto
            painter = painterResource(id= imageResource),
            contentDescription = "Imagen de ${producto.nombre}",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp)
        )

        Text( text = "${producto.nombre}, ")
    }
}


private fun obtieneImagen(context: Context, imagen: String?): Int {
    val nombre = imagen?.replace(".png", "") ?: "logo"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)

    return if(resourceId == 0) R.drawable.logo else resourceId
}




