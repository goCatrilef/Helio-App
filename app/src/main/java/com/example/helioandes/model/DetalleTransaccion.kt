package com.example.helioandes.model

import androidx.room.Entity


@Entity(tableName = "DetalleTransaccion",
    primaryKeys = ["transaccionId", "productoId"])
data class DetalleTransaccion(
    val transaccionId: Int,
    val productoId: Int,
    val cantidad: Int
)
