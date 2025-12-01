package com.example.helioandes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FormularioContacto")
data class FormularioContacto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val telefono: String,
    val tipoConsulta: String,
    val asunto: String,
    val mensaje: String,
)
