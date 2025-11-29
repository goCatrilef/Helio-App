package com.example.helioandes.model

@Entity
data class Usuario(
    val id: Int,
    val nombre: String,
    val password: String,
)

