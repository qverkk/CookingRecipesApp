package com.company

import com.company.controllers.MainController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage?) {
        val parent = javaClass.classLoader.getResource("com/company/fxmls/ApplicationView.fxml")
        if (parent == null) {
            stop()
        } else {
            val loader = FXMLLoader(parent)
            loader.setController(MainController())
            val root = loader.load<HBox>()
            val scene = Scene(root)
            primaryStage?.scene = scene
            primaryStage?.show()
        }
    }
}