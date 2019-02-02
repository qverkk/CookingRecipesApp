package com.company.controllers

import com.company.data.FileHelper
import com.company.data.Ingredients
import com.company.data.Meal
import com.company.presentation.IngredientPresentation
import com.company.presentation.NewIngredientPresentation
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class MainController : Initializable {

    @FXML
    private lateinit var addNewIngredientButton: Button

    @FXML
    private lateinit var ingredientsVbox: VBox
    @FXML
    private lateinit var proposedMealsVbox: VBox
    @FXML
    private lateinit var mealIngredientsVbox: VBox

    @FXML
    private lateinit var mealNameLbl: Label
    @FXML
    private lateinit var servingsLabel: Label

    @FXML
    private lateinit var mealInstructionsTextArea: TextArea

    private val ingredients = Ingredients()
    private val mealList = mutableListOf<Meal>()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        setActions()
        fillMeals()
        matchMeals()
    }

    fun addIngredient(ingredientName: String) {
        if (!ingredients.ingredients.contains(ingredientName)) {
            ingredients.ingredients.add(ingredientName)
            ingredientsVbox.children.add(IngredientPresentation(ingredientName, this))
            matchMeals()
        }
    }

    fun removeIngredient(ingredient: IngredientPresentation, ingredientName: String) {
        ingredientsVbox.children.remove(ingredient)
        ingredients.ingredients.remove(ingredientName)
        matchMeals()
    }

    private fun setActions() {
        addNewIngredientButton.setOnAction {
            NewIngredientPresentation(this)
        }
    }

    private fun matchMeals() {
        proposedMealsVbox.children.clear()

        for (meal in mealList) {
            var containsIngredients = true
            for (ingredient in ingredients.ingredients) {
                if (!meal.containsIngredient(ingredient)) {
                    containsIngredients = false
                    break
                }
            }

            if (containsIngredients) {
                val button = Button()
                button.text = meal.mealName
                button.setOnAction {
                    mealNameLbl.text = meal.mealName
                    servingsLabel.text = "Servings: ${meal.servings}"
                    mealInstructionsTextArea.text = meal.instructions
                    mealIngredientsVbox.children.clear()

                    for (tmp in meal.avaliableIngredients(ingredients.ingredients)) {
                        mealIngredientsVbox.children.add(
                                Label("$tmp +")
                        )
                    }
                    for (tmp in meal.missingIngredients(ingredients.ingredients)) {
                        mealIngredientsVbox.children.add(
                                Label("$tmp -")
                        )
                    }
                }
                proposedMealsVbox.children.add(button)
            }
        }
    }

    private fun fillMeals() {
        val fileHelper = FileHelper()
        mealList.addAll(fileHelper.generateMealList("com/company/files/recipes-db.txt"))
    }
}