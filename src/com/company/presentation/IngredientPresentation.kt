package com.company.presentation

import com.company.controllers.IngredientController
import com.company.controllers.MainController
import com.company.interfaces.Presentation
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage

class IngredientPresentation(
        val ingredientName: String,
        mainController: MainController,
        val stage: Stage = Stage(),
        override val viewPath: String = "com/company/fxmls/IngredientView.fxml")
    : HBox(), Presentation<IngredientController> {

    override val controller: IngredientController = IngredientController(this, mainController)

    init {
        val parent = FXMLLoader.getDefaultClassLoader().getResource(viewPath)
        if (parent != null) {
            val loader = FXMLLoader(parent)
            loader.setRoot(this)
            loader.setController(controller)
            val root = loader.load<HBox>()
            val scene = Scene(root)
            stage.scene = scene
        }
    }
}