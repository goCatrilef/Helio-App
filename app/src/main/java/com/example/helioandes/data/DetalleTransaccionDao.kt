package com.example.helioandes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.helioandes.model.DetalleTransaccion

@Dao
interface DetalleTransaccionDao {
    @Insert
    suspend fun insert(detalleTransaccion: DetalleTransaccion)

    @Update
    suspend fun update(detalleTransaccion: DetalleTransaccion)

    @Delete
    suspend fun delete(detalleTransaccion: DetalleTransaccion)

    @Query("SELECT * FROM DetalleTransaccion")
    suspend fun obtenerTodos(): List<DetalleTransaccion>

    @Query("SELECT * FROM DetalleTransaccion WHERE transaccionId = :transaccionId")
    suspend fun obtenerPorTransaccion(transaccionId: Int): List<DetalleTransaccion>




}