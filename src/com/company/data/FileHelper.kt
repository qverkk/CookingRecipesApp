package com.company.data

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.*

class FileHelper {

    private val mealHelper = MealHelper()

    fun generateMealList(fileName: String): List<Meal> {
        val meals = mutableListOf<Meal>()

        val test = this.javaClass.classLoader.getResourceAsStream(fileName)
//        val file = File(InputStreamReader(test))
//
//        println(file.exists())
//        if (!file.exists())
//            return emptyList()

        val jsonObject = getJsonObject(test)
        if (jsonObject.isJsonNull)
            return emptyList()

        for (entry in jsonObject.entrySet()) {
            val meal = mealHelper.convertToMeal(entry.value.asJsonObject)
            meals.add(meal)
        }
        return meals
    }

    private fun getJsonObject(file: File): JsonObject {
        val br = BufferedReader(FileReader(file))
        val stringBuilder = StringBuilder()

        br.lines().forEach {
            stringBuilder.append(it)
        }

        return JsonParser().parse(stringBuilder.toString()).asJsonObject
    }

    private fun getJsonObject(file: InputStream): JsonObject {
        val br = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()

        br.lines().forEach {
            stringBuilder.append(it)
        }

        return JsonParser().parse(stringBuilder.toString()).asJsonObject
    }
}