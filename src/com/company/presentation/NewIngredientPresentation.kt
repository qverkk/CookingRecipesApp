package com.company.presentation

import com.company.controllers.MainController
import com.company.controllers.NewIngredientController
import com.company.interfaces.Presentation
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage

class NewIngredientPresentation(
        private val mainController: MainController,
        private val stage: Stage = Stage(),
        override val viewPath: String = "com/company/fxmls/NewIngredientView.fxml",
        override val controller: NewIngredientController = NewIngredientController(stage, mainController)
) : Presentation<NewIngredientController> {

    init {
        val parent = FXMLLoader.getDefaultClassLoader().getResource(viewPath)
        if (parent != null) {
            val loader = FXMLLoader(parent)
            loader.setController(controller)
            val root = loader.load<VBox>()
            val scene = Scene(root)
            stage.scene = scene
            stage.show()
        }
    }
}