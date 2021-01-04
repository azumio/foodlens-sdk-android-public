package com.azumio.android.foodlenslibrary.model


import android.content.Intent
import android.util.Log
import com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

open class FoodSearchData()
 {
     @SerializedName("calories")
     var calories: Double =0.0
     @SerializedName("id")
     var id: String = ""
     @SerializedName("name")
     var name: String = ""
     @SerializedName("servingSize")
     var servingSize: SegmentResponse.FoodItem.ServingSize? = null

     @SerializedName("servingSizes")
     var servingSizes: List<SegmentResponse.FoodItem.ServingSize>? = null

     @SerializedName("source")
     var source: String = ""
     @SerializedName("validated")
     var validated: Boolean = false
     @SerializedName("statusId")
     var statusId: String? = null
     @SerializedName("category")
     var category: String? = null
     @SerializedName("important")
     var important: Boolean = false
     @SerializedName("userId")
     var userId: String? = null
     @SerializedName("brand")
     var brand: String? = null
     @SerializedName("numberOfServings")
     var numberOfServings: Double  = CaloriesManager.NUMBER_OF_SERVINGS
     @SerializedName("remoteid")
     var remoteid: String? = null
     @SerializedName("type")
     var type: String? =  null
     @SerializedName("parent_id")
     var parentId: String? = null
     @SerializedName("meal")
     var meal: String? = null

     @SerializedName("nutrition")
     var nutrition: SegmentResponse.FoodItem.Nutrition? = null

     @SerializedName("foods")
     var foods: List<FoodSearchData>? = null



     var nutritionParsed: Boolean = false

     fun  setNutritionFromMap(map: Map<String,Double>)
     {
        this.nutrition = nutritionFromMap(map)
     }

     fun  jsonString():String
     {
         return Gson().toJson(this)
     }

     fun getNutritionAsMap():Map<String,Double>
     {
         val gson = Gson()

         this.nutrition?.let {
             val nut = gson.toJson(it)
             val gsonType =
                 object : TypeToken<HashMap<String, Double>>() {}.type

            return gson.fromJson(nut,gsonType)


         }?: kotlin.run { return emptyMap() }
     }



     companion object {
          fun updateFoodItemFromIntent(dataItem: FoodSearchData, intent: Intent) {
             val servingUnit = intent.extras!!.getString(CaloriesManager.PROPERTY_UNIT)
             val servingWeight =
                 intent.extras!!.getString(CaloriesManager.PROPERTY_SERVING_WEIGHT)
             val numberOfServings =
                 intent.extras!!.getString(CaloriesManager.PROPERTY_NO_OF_SERVINGS)
             dataItem.servingSize = SegmentResponse.FoodItem.ServingSize(servingWeight = (servingWeight ?: CaloriesManager.CALORIES_WEIGHT).toDouble(),unit = servingUnit?: CaloriesManager.CALORIES_UNIT )
             dataItem.numberOfServings = numberOfServings?.toDouble() ?: 0.0
         }

         fun initFromJson(json:String):FoodSearchData
         {
             val gson = Gson()
             val gsonType =
                 object : TypeToken<FoodSearchData>() {}.type
             return gson.fromJson<FoodSearchData>(json,gsonType)
         }

         fun nutritionFromMap(map: Map<String,Double>):Nutrition?
         {
             try {
                 val gson = Gson()
                 val gsonType =
                     object : TypeToken<HashMap<String, Double>>() {}.type
                 val gsonString = gson.toJson(map, gsonType)
                 val nut = gson.fromJson(
                     gsonString,
                     Nutrition::class.java
                 )

                return nut
             }
             catch (e:Exception)
             {
                 Log.i("FoodSearchData",e.localizedMessage)
             }
             return  null
         }

         const val DEFAULT_SERVING_WEIGHT = 1.0
     }
 }