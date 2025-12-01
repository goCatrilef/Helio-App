package com.example.helioandes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.helioandes.model.Usuario

//Aca van los metodos abstractos.CRUD viene por Defecto y Query son las consultas personalizadas.
//Query - Select * From

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertar(usuario: Usuario)

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Delete
    suspend fun eliminar(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    suspend fun obtenerUsuarios(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE id = :id")
    suspend fun obtenerUsuarioPorId(id: Int): Usuario

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre AND contrasena = :contrasena")
    suspend fun obtenerLogin(nombre: String, contrasena: String): Usuario

}