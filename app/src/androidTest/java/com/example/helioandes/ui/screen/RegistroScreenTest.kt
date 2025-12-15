package com.example.helioandes.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun registroForm_validationMessagesShown() {
        val navController = TestNavHostController(composeTestRule.activity)

        composeTestRule.setContent {
            RegistroScreen(navController = navController)
        }

        // 1) Empty form -> should ask for name
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("Por favor ingresa tu nombre").assertIsDisplayed()

        // 2) Fill name, leave email -> should ask for email
        composeTestRule.onNodeWithText("Nombre completo").performTextInput("Gonzalo")
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("Por favor ingresa tu correo").assertIsDisplayed()

        // 3) Fill invalid email -> should ask for valid email
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("invalid")
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("Ingresa un correo válido").assertIsDisplayed()

        // 4) Make email valid and click -> should ask for password
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("@example.com")
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("Por favor ingresa una contraseña").assertIsDisplayed()

        // 5) Enter short password -> should show length error
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123")
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("La contraseña debe tener al menos 6 caracteres").assertIsDisplayed()

        // 6) Enter password and mismatching confirmation -> should show mismatch
        composeTestRule.onNodeWithText("Contraseña").performTextInput("456") // now 123456
        composeTestRule.onNodeWithText("Confirmar contraseña").performTextInput("000000")
        composeTestRule.onNodeWithText("Crear Cuenta").performClick()
        composeTestRule.onNodeWithText("Las contraseñas no coinciden").assertIsDisplayed()
    }
}
