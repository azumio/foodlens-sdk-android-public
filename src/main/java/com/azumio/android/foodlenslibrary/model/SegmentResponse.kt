package com.azumio.android.foodlenslibrary.model


import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import com.google.gson.annotations.SerializedName
import java.util.*

data class SegmentResponse(

    @SerializedName("foods")
    var foods: List<TraceSegment>,
    @SerializedName("imagecache_id")
    val imagecacheId: String,
    @SerializedName("is_food")
    val isFood: Boolean,
    @SerializedName("lang")
    val lang: String
) {

    data class TraceSegment(
        @SerializedName("bounding_box")
        val boundingBox: BoundingBox,
        @SerializedName("center")
        val center: Center,
        @SerializedName("is_food")
        val isFood: Boolean,
        @SerializedName("is_packaged_good")
        val isPackagedGood: Boolean,
        @SerializedName("mask")
        val mask: Mask,
        @SerializedName("_model")
        val model: String,
        @SerializedName("not_food")
        val notFood: Boolean,
        @SerializedName("_not_food_labels")
        val notFoodLabels: List<String>,
        @SerializedName("_not_food_ratio")
        val notFoodRatio: Double,
        @SerializedName("_not_food_score")
        val notFoodScore: Int,
        @SerializedName("_packaged_good_score")
        val packagedGoodScore: Int,
        @SerializedName("results")
        val categories: List<Category>

    ) {

        val id: String
            get() {
                return "${center.x}-${center.y}"
            }

        data class BoundingBox(
            @SerializedName("height")
            val height: Double,
            @SerializedName("width")
            val width: Double,
            @SerializedName("x")
            val x: Double,
            @SerializedName("y")
            val y: Double
        )

        data class Center(
            @SerializedName("x")
            val x: Double,
            @SerializedName("y")
            val y: Double
        ){
            override fun equals(other: Any?): Boolean{
                if (this === other) return true
                if (other?.javaClass != javaClass) return false

                other as Center
                val rad = 0.1
                return (other.x - x) * (other.x - x) +
                        (other.y - y) * (other.y - y) <= rad * rad

            }
        }

        data class Mask(
            @SerializedName("data")
            val `data`: String,
            @SerializedName("encoding")
            val encoding: String
        )

        data class Category(
            val groupId: String = generateId(),
            @SerializedName("group")
            val group: String,
            @SerializedName("items")
            val items: List<FoodItem>
        )
        {
            companion object {
                private const val prefix = "CAT_"

                private fun generateId(): String {
                    return prefix + UUID.randomUUID().toString()
                }
            }
        }
    }


    data class FoodItem(

        @SerializedName("combine_results_foods_ids")
        val combineResultsFoodsIds: List<Int>,
        @SerializedName("created")
        val created: Long,
        @SerializedName("food_id")
        val foodId: String,
        @SerializedName("generic")
        val generic: Boolean,
        @SerializedName("group")
        val group: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("ingredients")
        val ingredients: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("modified")
        val modified: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("nutrition")
        val nutrition: Nutrition,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("score")
        val score: Int,
        @SerializedName("searchable")
        val searchable: Boolean,
        @SerializedName("servingSizes")
        val servingSizes: List<ServingSize>,
        @SerializedName("source")
        val source: String,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("userId")
        val userId: String,
        @SerializedName("validated")
        val validated: Boolean,
        @SerializedName("meal")
        var meal: String? = null,
        @SerializedName("servingSize")
        var servingSize: ServingSize?,
        @SerializedName("numberOfServings")
        var numberOfServings: Double = CaloriesManager.NUMBER_OF_SERVINGS

    ) {
        data class Nutrition(
            @SerializedName("calcium")
            val calcium: Double,
            @SerializedName("calories")
            var calories: Double,
            @SerializedName("cholesterol")
            val cholesterol: Double,
            @SerializedName("dietaryFiber")
            val dietaryFiber: Double,
            @SerializedName("iron")
            val iron: Double,
            @SerializedName("monounsaturatedFat")
            val monounsaturatedFat: Double,
            @SerializedName("polyunsaturatedFat")
            val polyunsaturatedFat: Double,
            @SerializedName("potassium")
            val potassium: Double,
            @SerializedName("protein")
            val protein: Double,
            @SerializedName("saturatedFat")
            val saturatedFat: Double,
            @SerializedName("sodium")
            val sodium: Double,
            @SerializedName("sugars")
            val sugars: Double,
            @SerializedName("totalCarbs")
            val totalCarbs: Double,
            @SerializedName("totalFat")
            val totalFat: Double,
            @SerializedName("transFat")
            val transFat: Double,
            @SerializedName("vitaminA")
            val vitaminA: Double,
            @SerializedName("vitaminC")
            val vitaminC: Double
        )

        data class ServingSize(
            @SerializedName("servingWeight")
            var servingWeight: Double,
            @SerializedName("unit")
            var unit: String
        )

        fun toFoodSearchData():FoodSearchData
        {
            val data = FoodSearchData()
            data.nutrition = this.nutrition
            data.id = this.id
            data.name = this.name
            data.servingSize = this.servingSize
            data.servingSizes = this.servingSizes
            data.numberOfServings = this.numberOfServings
            return  data
        }

        companion object {

             fun initWithSearchData(data:FoodSearchData): FoodItem {
                return FoodItem(emptyList(),Date().time,data.id,true,"search",UUID.randomUUID().toString(),
                    data.name,data.name,Date().time,data.name,data.nutrition!!,"",100,true,data.servingSizes ?: emptyList(),data.source,
                    "","",data.validated,data.meal,data.servingSize,data.numberOfServings)
            }
        }

    }


}