package com.example.helioandes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.helioandes.data.HelioDataBase
import com.example.helioandes.utils.SessionManager

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val database = HelioDataBase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()
    private val sessionManager = SessionManager(application)

    suspend fun login(nombre: String, contrasena: String): Boolean {
        val usuarioEncontrado = usuarioDao.obtenerLogin(nombre, contrasena)
        
        return if (usuarioEncontrado != null) {
            sessionManager.saveUserSession(
                userId = usuarioEncontrado.id,
                userName = usuarioEncontrado.nombre,
                userEmail = usuarioEncontrado.correo
            )
            true
        } else {
            false
        }
    }
}