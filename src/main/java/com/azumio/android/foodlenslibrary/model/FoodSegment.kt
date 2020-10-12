package com.azumio.android.foodlenslibrary.model

import com.google.gson.annotations.SerializedName

class FoodSegment (
    val identifier:String,
    val boundingBox: SegmentResponse.TraceSegment.BoundingBox,
    val center: SegmentResponse.TraceSegment.Center,
    var userCenter: SegmentResponse.TraceSegment.Center? = null,
    val isFood: Boolean,
    val notFoodRatio: Double,
    var categories:List<FoodSuggestionCategory>)
{
    var deleted:Boolean = false
    var custom:Boolean = false
    var foodLogs:ArrayList<FoodLog> = ArrayList<FoodLog>()
    val hasCategories:Boolean
    get() {return categories.isNotEmpty()
    }

}


class FoodSuggestionCategory(var identifier:String,var group:String, var suggestions:List<FoodSuggestion>)
{

}

class FoodSuggestion(var identifier:String,var foodItem:SegmentResponse.FoodItem)
{
    init {
        print("here")
       if(foodItem.servingSize == null && foodItem.servingSizes.count() >0)
       {
           foodItem.servingSize = foodItem.servingSizes.first()
       }
    }
}