package com.example.helioandes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.helioandes.data.HelioDataBase
import com.example.helioandes.model.Usuario
import com.example.helioandes.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(application: Application): AndroidViewModel(application) {
    private val database = HelioDataBase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()
    private val sessionManager = SessionManager(application)

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    fun cargarUsuarioActual() {
        viewModelScope.launch {
            val userId = sessionManager.getUserId()
            if (userId != -1) {
                val usuarioEncontrado = usuarioDao.obtenerUsuarioPorId(userId)
                _usuario.value = usuarioEncontrado
            }
        }
    }

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            val usuarioEncontrado = usuarioDao.obtenerUsuarioPorId(id)
            _usuario.value = usuarioEncontrado
        }
    }

    suspend fun actualizarUsuario(usuario: Usuario) {
        usuarioDao.actualizar(usuario)
        _usuario.value = usuario
        
        sessionManager.saveUserSession(
            userId = usuario.id,
            userName = usuario.nombre,
            userEmail = usuario.correo
        )
    }
    
    fun cerrarSesion() {
        sessionManager.clearSession()
        _usuario.value = null
    }
}