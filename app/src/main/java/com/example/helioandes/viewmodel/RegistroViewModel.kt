package com.example.helioandes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.helioandes.data.HelioDataBase
import com.example.helioandes.model.Usuario
import com.example.helioandes.utils.SessionManager

class RegistroViewModel(application: Application) : AndroidViewModel(application) {
    private val database = HelioDataBase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()
    private val sessionManager = SessionManager(application)

    suspend fun registrarUsuario(nombre: String, correo: String, contrasena: String): Boolean {
        return try {
            val usuariosExistentes = usuarioDao.obtenerUsuarios()
            val usuarioExiste = usuariosExistentes.any { it.correo == correo }
            
            if (usuarioExiste) {
                false 
            } else {
                val nuevoUsuario = Usuario(
                    nombre = nombre,
                    correo = correo,
                    contrasena = contrasena
                )
                usuarioDao.insertar(nuevoUsuario)
                
                val usuarioInsertado = usuarioDao.obtenerLogin(correo, contrasena)
                
                usuarioInsertado?.let {
                    sessionManager.saveUserSession(
                        userId = it.id,
                        userName = it.nombre,
                        userEmail = it.correo
                    )
                }
                
                true 
            }
        } catch (e: Exception) {
            false
        }
    }
}