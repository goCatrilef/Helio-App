package com.example.helioandes.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.helioandes.model.Usuario
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeRepositoryTest {

	private lateinit var db: HelioDataBase
	private lateinit var usuarioDao: UsuarioDao

	@Before
	fun createDb() {
		val context = InstrumentationRegistry.getInstrumentation().targetContext
		db = Room.inMemoryDatabaseBuilder(context, HelioDataBase::class.java)
			.allowMainThreadQueries()
			.build()
		usuarioDao = db.usuarioDao()
	}

	@After
	fun closeDb() {
		db.close()
	}

	@Test
	fun obtenerUsuarioPorId_returnsCorrectUser() = runBlocking {
		val usuario = Usuario(nombre = "TestUser", correo = "test@example.com", contrasena = "pass123")

		usuarioDao.insertar(usuario)

		val usuarios = usuarioDao.obtenerUsuarios()
		assertTrue(usuarios.isNotEmpty())

		val id = usuarios[0].id

		val fetched = usuarioDao.obtenerUsuarioPorId(id)

		assertEquals("TestUser", fetched.nombre)
		assertEquals("test@example.com", fetched.correo)
		assertEquals("pass123", fetched.contrasena)
	}
}