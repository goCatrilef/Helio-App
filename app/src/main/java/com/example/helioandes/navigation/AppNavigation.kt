package com.example.helioandes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helioandes.ui.screen.CarritoScreen
import com.example.helioandes.ui.screen.ContactoScreen
import com.example.helioandes.ui.screen.CotizarScreen
import com.example.helioandes.ui.screen.EditarPerfilScreen
import com.example.helioandes.ui.screen.HomeScreen
import com.example.helioandes.ui.screen.LoginScreen
import com.example.helioandes.ui.screen.PerfilScreen
import com.example.helioandes.ui.screen.RegistroScreen
import com.example.helioandes.ui.screen.SplashScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController ()
                                                        //AQUI VA la pogina inicio 1.
    NavHost(navController = navController,startDestination = "splash"){
        //AQUI VAN LAS PAGINAS.
        composable ("splash"){ SplashScreen(navController = navController) }
        composable("login") { LoginScreen(navController = navController) }
        composable("registro") { RegistroScreen(navController = navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("cotizar") { CotizarScreen(navController = navController) }
        composable("contacto") { ContactoScreen(navController = navController) }
        composable("carrito") { CarritoScreen(navController = navController) }
        composable("perfil") { PerfilScreen(navController = navController) }
        composable("editar_perfil") { EditarPerfilScreen(navController = navController) }
    }
}