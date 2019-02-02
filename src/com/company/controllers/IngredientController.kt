package com.company.controllers

import com.company.presentation.IngredientPresentation
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import java.net.URL
import java.util.*

class IngredientController(
        private val presentation: IngredientPresentation,
        private val mainController: MainController
) : Initializable {

    @FXML
    private lateinit var removeIngredientButton: Button

    @FXML
    private lateinit var ingredientNameTextArea: TextArea

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        ingredientNameTextArea.text = presentation.ingredientName

        removeIngredientButton.setOnAction {
            mainController.removeIngredient(presentation, ingredientNameTextArea.text)
        }
    }
}