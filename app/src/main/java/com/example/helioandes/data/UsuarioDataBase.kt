package com.example.helioandes.data

// Estas clases deben ser abstract.

abstract class UsuarioDataBase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDAO
} {


}