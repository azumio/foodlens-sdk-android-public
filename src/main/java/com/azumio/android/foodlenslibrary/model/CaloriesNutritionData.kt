package com.azumio.android.foodlenslibrary.model


import com.google.gson.annotations.SerializedName

data class CaloriesNutritionData(
    @SerializedName("bold")
    val bold: Int,
    @SerializedName("decimal")
    val decimal: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("premium")
    val premium: Int,
    @SerializedName("primary")
    val primary: Int,
    @SerializedName("tabbed")
    val tabbed: Int,
    @SerializedName("unit")
    val unit: String
)