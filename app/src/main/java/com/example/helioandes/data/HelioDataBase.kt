package com.example.helioandes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.helioandes.model.Usuario
import com.example.helioandes.model.Producto
import com.example.helioandes.model.Transaccion
import com.example.helioandes.model.DetalleTransaccion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Estas clases deben ser abstract.
//Cada vez debemos ir cambiiando las versiones de la base de datos.Porque las construye de nuevamente

@Database(
    entities = [
        Usuario::class,
        Producto::class,
        Transaccion::class,
        DetalleTransaccion::class,
    ],
    version = 1)
abstract class HelioDataBase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun productoDao(): ProductoDao
    abstract fun transaccionDao(): TransaccionDao
    abstract fun detalleTransaccionDao(): DetalleTransaccionDao


    companion object{
        private var database: HelioDataBase? = null

        fun getDatabase(context: Context): HelioDataBase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    HelioDataBase::class.java,
                    "helio.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Aquí puedes realizar acciones adicionales al crear la base de datos
                        CoroutineScope(Dispatchers.IO).launch {
                            insertarDatosPorDefecto(database!!)

                        }
                    }

                })
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build()
            }
            //si o si va crear la db si no bota el programa
            return database!!
        }

        private suspend fun insertarDatosPorDefecto(db: HelioDataBase) {
            val usuarioDao = db.usuarioDao()
            val productoDao = db.productoDao()


            val usuarios = listOf(
                Usuario(nombre = "Gonzalo", correo = "go@test.cl", contrasena = "123456"),
                Usuario(nombre = "Juan Carlos",  correo = "jc@test.cl",contrasena = "123456"),
                Usuario(nombre = "Daniel", correo = "da@test.cl", contrasena = "123456")

            )

            usuarios.forEach {usuarioDao.insertar(it) }

            val productos = listOf(
                Producto(nombre = "Panel Solar 450w", descripcion = "Ideal para climas soleados", precio = 189990, imagen = "logo.png"),
                Producto(nombre = "Inversor 5kW Hibrido", descripcion = "Compatible con baterias", precio = 899990, imagen = "logo.png"),
                Producto(nombre = "Montaje", descripcion = "Estructura para Techo Teja", precio = 159990, imagen = "logo.png"),
            )

            productos.forEach { productoDao.insertar(it) }






        }


    }




}


