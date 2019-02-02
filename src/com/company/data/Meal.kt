package com.company.data

data class Meal(var mealName: String = "",
                var servings: Int = 0,
                var ingredients: MutableList<String> = mutableListOf(),
                var instructions: String = "") {

    fun containsIngredient(ingredient: String): Boolean {
        for (tmp in ingredients) {
            if (tmp.toLowerCase().contains(ingredient.toLowerCase())) {
                return true
            }
        }
        return false
    }

    private fun findIngredient(name: String, list: MutableList<String>): String {
        for (tmp in list) {
            if (tmp.toLowerCase().contains(name.toLowerCase())) {
                return tmp
            }
        }
        return ""
    }

    fun avaliableIngredients(allIngredients: List<String>): List<String> {
        val result: MutableList<String> = mutableListOf()
        for (tmp in allIngredients) {
            val foundText = findIngredient(tmp, ingredients)
            if (!foundText.isEmpty()) {
                result.add(foundText)
            }
        }
        return result
    }

    fun missingIngredients(allIngredients: List<String>): List<String> {
        val result: MutableList<String> = ingredients.toMutableList()
        for (tmp in allIngredients) {
            val foundText = findIngredient(tmp, result)
            if (!foundText.isEmpty()) {
                result.remove(foundText)
            }
        }
        return result
    }
}