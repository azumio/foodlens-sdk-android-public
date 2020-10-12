package com.azumio.android.foodlenslibrary.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class FoodCheckin(
    @SerializedName("food_logs")
    val foodLogs: List<FoodLog>?,
    @SerializedName("foodrecognition_imagecache_id")
    val foodrecognitionImagecacheId: String?,
    @SerializedName("foodrecognition_trace_segments")
    val foodrecognitionTraceSegments: List<FoodrecognitionTraceSegment>?,
    @SerializedName("nutrition")
    val nutrition: SegmentResponse.FoodItem.Nutrition?,
    @SerializedName("photos")
    val photos: List<Photo>?,
    @SerializedName("remoteid")
    val remoteid: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("timezone")
    val timezone: Double,
    @SerializedName("type")
    val type: String
) {

    companion object {
        fun initFromJson(json:String):FoodCheckin
        {
            val gson = Gson()
            val gsonType =
                object : TypeToken<FoodCheckin>() {}.type
            return gson.fromJson<FoodCheckin>(json,gsonType)
        }
    }
    fun  jsonString():String
    {
        return Gson().toJson(this)
    }

    data class FoodLog(
        @SerializedName("meal")
        val meal: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("numberOfServings")
        val numberOfServings: Double,
        @SerializedName("nutrition")
        val nutrition: SegmentResponse.FoodItem.Nutrition?,
        @SerializedName("parent_id")
        val parentId: String?,
        @SerializedName("remoteid")
        val remoteid: String,
        @SerializedName("servingSize")
        val servingSize: SegmentResponse.FoodItem.ServingSize?,
        @SerializedName("statusId")
        val statusId: String?,
        @SerializedName("suggestionGroup")
        val suggestionGroup: SuggestionGroup?,
        @SerializedName("suggestionSegments")
        val suggestionSegments: List<FoodrecognitionTraceSegment>?,
        @SerializedName("timestamp")
        val timestamp: Long,
        @SerializedName("type")
        val type: String,
        @SerializedName("validated")
        val validated: Boolean
    ) {
        data class SuggestionGroup(
            @SerializedName("group")
            val group: String
        )


    }

    data class FoodrecognitionTraceSegment(
        @SerializedName("bounding_box")
        val boundingBox: SegmentResponse.TraceSegment.BoundingBox,
        @SerializedName("center")
        val center: SegmentResponse.TraceSegment.Center,
        @SerializedName("id")
        val id: String,
        @SerializedName("is_food")
        val isFood: Boolean,
        @SerializedName("score")
        val score: Int
    )


    data class Photo(
        @SerializedName("href")
        val href: String
    )
}