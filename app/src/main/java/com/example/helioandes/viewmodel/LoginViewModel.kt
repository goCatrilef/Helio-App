package com.example.helioandes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.helioandes.data.HelioDataBase

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val database = HelioDataBase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()

    suspend fun login(nombre: String, contrasena: String): Boolean {
        val usuarioEncontrado = usuarioDao.obtenerLogin(nombre, contrasena)
        return usuarioEncontrado != null
    }


}