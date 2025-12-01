package com.example.helioandes.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.helioandes.data.HelioDataBase
import com.example.helioandes.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ProductoViewModel (application: Application): AndroidViewModel(application) {
    private val database = HelioDataBase.getDatabase(application)
    private val productoDao = database.productoDao()

    private val _productos = MutableStateFlow(emptyList<Producto>())

    val productos: MutableStateFlow<List<Producto>> = _productos

    fun cargarProductos(){
        viewModelScope.launch {
            val lista = productoDao.obtenerTodos()
            _productos.value = lista

        }
    }

}
