package com.example.helioandes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.helioandes.model.FormularioContacto
import com.example.helioandes.model.Usuario

@Dao
interface FormularioContactoDao {
    @Insert
    suspend fun insertar(formularioContacto: FormularioContacto)

    @Update
    suspend fun actualizar(formularioContacto: FormularioContacto)

    @Delete
    suspend fun eliminar(formularioContacto: FormularioContacto)

}