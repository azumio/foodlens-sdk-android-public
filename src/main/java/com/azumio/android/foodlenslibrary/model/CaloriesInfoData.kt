package com.azumio.android.foodlenslibrary.model


import com.google.gson.annotations.SerializedName

data class CaloriesInfoData(
    @SerializedName("drv")
    val drv: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("recommended")
    val recommended: String,
    @SerializedName("unit")
    val unit: String
)