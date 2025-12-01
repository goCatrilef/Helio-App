package com.example.helioandes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.helioandes.data.HelioDataBase
import com.example.helioandes.model.FormularioContacto

class ContactoViewModel(application: Application): AndroidViewModel(application) {

    private val database = HelioDataBase.getDatabase(application)
    private val formularioContactoDao = database.formularioContactoDao()

    suspend fun guardarFormularioContacto(formularioContacto: FormularioContacto) {
        formularioContactoDao.insertar(formularioContacto)
    }


}