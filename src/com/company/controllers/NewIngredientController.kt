package com.company.controllers

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.net.URL
import java.util.*

class NewIngredientController(private val primaryStage: Stage, private val mainController: MainController) : Initializable {

    @FXML
    private lateinit var ingredientNameTextField: TextField

    @FXML
    private lateinit var addButton: Button
    @FXML
    private lateinit var cancelButton: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        cancelButton.setOnAction {
            primaryStage.close()
        }

        addButton.setOnAction {
            val text = ingredientNameTextField.text
            if (!text.isEmpty()) {
                mainController.addIngredient(text)
            }
        }
    }
}