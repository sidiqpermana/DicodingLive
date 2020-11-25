package com.nbs.dicodinglive

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.nbs.dicodinglive.main.App
import com.nbs.dicodinglive.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val radiusLabel: String by lazy {
       InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.label_radius)
    }
    private val calculateLabel: String by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.label_calculate)
    }
    private val deleteLabel: String by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.label_delete)
    }

    private val defaultResultLabel: String = "0.0"

    @Before
    fun setUp(){
        composeTestRule.setContent {
            App()
        }

        composeTestRule.onRoot().printToLog("TAG")
    }

    @Test
    fun testAllViewDisplayed(){
        composeTestRule.onNodeWithText(radiusLabel, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(calculateLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(deleteLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(defaultResultLabel).assertIsDisplayed()
    }

    @Test
    fun testCalculation(){
        findTextInputField("RadiusField").performTextInput("58")
        composeTestRule.onNodeWithText(calculateLabel).performClick()
        composeTestRule.onNodeWithText("24.0").assertIsDisplayed()
    }

    @Test
    fun testCalculationAndReset(){
        findTextInputField("RadiusField").performTextInput("58")
        composeTestRule.onNodeWithText(calculateLabel).performClick()
        composeTestRule.onNodeWithText("24.0").assertIsDisplayed()
        composeTestRule.onNodeWithText(deleteLabel).performClick()
        composeTestRule.onNodeWithText(defaultResultLabel).assertIsDisplayed()
    }

    private fun findTextInputField(tag: String): SemanticsNodeInteraction {
        return composeTestRule.onNode(
                hasInputMethodsSupport() and
                        hasTestTag(tag))

    }
}