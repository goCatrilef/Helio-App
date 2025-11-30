package com.example.helioandes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helioandes.ui.screen.SplashScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController ()
                                                        //AQUI VA la pogina inicio 1.
    NavHost(navController = navController,startDestination = "splash"){
        //AQUI VAN LAS PAGINAS.
        composable ("splash"){ SplashScreen(navController = navController) }
    }


}