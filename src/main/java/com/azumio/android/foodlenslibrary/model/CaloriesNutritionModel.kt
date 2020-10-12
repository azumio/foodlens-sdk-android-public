package com.azumio.android.foodlenslibrary.model


import com.google.gson.annotations.SerializedName

data class CaloriesNutritionModel(
    @SerializedName("info")
    val info: Map<String, CaloriesInfoData>,
    @SerializedName("nutrition")
    val nutrition: List<CaloriesNutritionData>
)