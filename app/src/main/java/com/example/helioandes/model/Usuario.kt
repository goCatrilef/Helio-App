package com.example.helioandes.model

import androidx.room.Entity

@Entity
data class Usuario(
    val id: Int,
    val nombre: String,
    val password: String,
)

