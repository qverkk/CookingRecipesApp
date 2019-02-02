package com.company.data

import com.google.gson.JsonObject
import java.util.*

class MealHelper {

    fun convertToMeal(jsonObject: JsonObject): Meal {
        val meal = Meal()
        meal.mealName = getElementAsString("name", jsonObject)
        meal.servings = getElementAsInt("servings", jsonObject)
        meal.instructions = getElementAsString("instructions", jsonObject)
        meal.ingredients = getIngredients(jsonObject)
        return meal
    }

    private fun getIngredients(jsonObject: JsonObject): MutableList<String> {
        val ingredients = ArrayList<String>()
        for (element in jsonObject.get("ingredients").asJsonArray) {
            ingredients.add(element.asString.toLowerCase().replace("\r", ""))
        }
        return ingredients
    }

    private fun getElementAsString(objectName: String, jsonObject: JsonObject): String {
        return jsonObject.get(objectName).asString
    }

    private fun getElementAsInt(objectName: String, jsonObject: JsonObject): Int {
        return jsonObject.get(objectName).asInt
    }
}