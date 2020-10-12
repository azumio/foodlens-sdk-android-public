package com.azumio.android.foodlenslibrary.model

data class FoodLog(var underlyingFoodLog: SegmentResponse.FoodItem
                   , val suggestionId: String,
                   val categoryId:String) {


}