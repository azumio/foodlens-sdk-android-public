package com.azumio.android.foodlenslibrary.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

open class CaloriesSearchLog {

    @SerializedName("resultFoods")
    var resultFoods: List<String> = emptyList()

    @SerializedName("searchTerm")
    var searchTerm: String = ""

    @SerializedName("selectedFoodId")
    var selectedFoodId: List<String>? = null

    fun addItemId(itemId: String) {
        val selectedFoods =
            if (selectedFoodId != null) ArrayList(selectedFoodId) else mutableListOf<String>()
        selectedFoods.add(itemId)
        this.selectedFoodId = selectedFoods
    }

    fun removeItemId(itemId: String?) {
        val selectedFoods =
            if (selectedFoodId != null) ArrayList(selectedFoodId) else mutableListOf<String>()
        selectedFoods.remove(itemId)
        this.selectedFoodId = selectedFoods
    }

    fun  jsonString():String
    {
        return Gson().toJson(this)
    }



}