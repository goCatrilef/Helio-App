package com.example.helioandes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaccion")
data class Transaccion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fecha: String,
    val usuarioId: Int
)
