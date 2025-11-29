package com.example.helioandes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.helioandes.model.Transaccion

@Dao
interface TransaccionDao {
    @Insert
    suspend fun insertar(transaccion: Transaccion)

    @Update
    suspend fun actualizar(transaccion: Transaccion)

    @Delete
    suspend fun eliminar(transaccion: Transaccion)

    @Query("SELECT * FROM Transaccion")
    suspend fun obtenerTodas(): List<Transaccion>

    @Query("SELECT * FROM Transaccion WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Transaccion

    @Query("SELECT * FROM Transaccion WHERE usuarioId = :usuarioId")
    suspend fun obtenerPorUsuario(usuarioId: Int): List<Transaccion>
}