package com.example.helioandes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.helioandes.model.Usuario

@Dao
interface UsuarioDao {
    //Aca van los metodos abstractos.CRUD viene por Defecto y Query son las consultas personalizadas.
    //Query - Select * From
    @Insert
    suspend fun insertar(usuario: Usuario)

    suspend fun eliminar(usuario: Usuario)

    suspend fun actualizar(usuario: Usuario)


    @Query("SELECT * FROM usuario")
    suspend fun obtenerUsuarios(): List<Usuario>

}