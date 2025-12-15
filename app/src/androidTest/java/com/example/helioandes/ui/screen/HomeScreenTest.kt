package com.example.helioandes.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.helioandes.model.Producto
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

	@get:Rule
	val composeTestRule = createAndroidComposeRule<ComponentActivity>()

	@Test
	fun productoItem_showsImageAndName() {
		val producto = Producto(
			id = 1,
			nombre = "Panel Solar X",
			descripcion = "Descripción de prueba",
			precio = 100,
			imagen = "logo.png"
		)

		composeTestRule.setContent {
			ProductoItem(producto = producto)
		}

		// Verifica que el texto con el nombre del producto se muestre
		composeTestRule.onNodeWithText("Panel Solar X, ").assertIsDisplayed()

		// Verifica que la imagen con la descripción de contenido exista
		composeTestRule.onNodeWithContentDescription("Imagen de Panel Solar X").assertIsDisplayed()
	}
}