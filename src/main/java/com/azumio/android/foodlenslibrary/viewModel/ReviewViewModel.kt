package com.azumio.android.foodlenslibrary.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azumio.android.foodlenslibrary.model.FoodCheckin
import com.azumio.android.foodlenslibrary.model.FoodSearchData
import com.azumio.android.foodlenslibrary.model.FoodSegment
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class ReviewViewModelFactory(private val uri: Uri,private val segments: List<FoodSegment>,val imageCacheId:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(Uri::class.java, List::class.java, String::class.java)
            .newInstance(uri,segments,imageCacheId)
}



class ReviewViewModel(val imageUri: Uri,val foodSegments: List<FoodSegment>,val imageCacheId:String) : ViewModel() {

    data class NutrientSummary(val nutrientTag:String, val value:Double)

    val nutrientSummary: MutableLiveData<List<NutrientSummary>> = MutableLiveData()
    val checkin: MutableLiveData<FoodCheckin> = MutableLiveData()

    fun loadNutrientProgress() {
        val foodLogs = foodSegments.flatMap { it.foodLogs.map { log -> log.underlyingFoodLog.toFoodSearchData() } }
       val nutrition = CaloriesManager.getNutritionSummation(foodLogs)
       var nutrients:ArrayList<NutrientSummary> = ArrayList()
       val nutrientTags = listOf( TOTAL_FAT, PROTEIN, TOTAL_CARBS, DIETARY_FIBER, CALORIES)
        nutrientTags.forEach { tag ->
            nutrition[tag]?.let {
                nutrients.add(NutrientSummary(tag,it))
            }
        }

        nutrientSummary.postValue(nutrients)
    }

    fun  processData( meal:String)
    {
        val segments = foodSegments.filter { it.foodLogs.size > 0 }
        val traceSegments = foodSegments.map { FoodCheckin.FoodrecognitionTraceSegment(it.boundingBox,it.center,it.identifier,it.isFood,1) }
        val statusId = UUID.randomUUID().toString()
        val timezone = TimeUnit.HOURS.convert(TimeZone.getDefault().rawOffset.toLong(), TimeUnit.MILLISECONDS).toDouble()
        val foodLogs = segments.flatMap {
            seg -> seg.foodLogs.map {
                val parentId = it.underlyingFoodLog.parentId
                val suggestionSegments = listOf(FoodCheckin.FoodrecognitionTraceSegment(seg.boundingBox,
                    seg.center,
                    seg.identifier,
                    seg.isFood,
                    it.underlyingFoodLog.score))
                FoodCheckin.FoodLog(it.underlyingFoodLog.meal ?: meal,
                    it.underlyingFoodLog.name,
                    it.underlyingFoodLog.numberOfServings,
                    it.underlyingFoodLog.nutrition,
                    parentId,
                    it.underlyingFoodLog.id,
                    it.underlyingFoodLog.servingSize,
                    statusId,
                    FoodCheckin.FoodLog.SuggestionGroup(it.underlyingFoodLog.group),
                    suggestionSegments,
                    Date().time,
                    CaloriesManager.LOG_TYPE_FOOD,
                    it.underlyingFoodLog.validated)
            }
        }
        val foodSearchData = foodSegments.flatMap { log -> log.foodLogs.map { it.underlyingFoodLog.toFoodSearchData() } }
        val nutrients = CaloriesManager.getNutritionSummation(foodSearchData)

        val checkin = FoodCheckin(foodLogs,
                this.imageCacheId,
                traceSegments,
                FoodSearchData.nutritionFromMap(nutrients),
                listOf(FoodCheckin.Photo(imageUri.toString())),
                UUID.randomUUID().toString(),
                Date().time,
                timezone,
                CaloriesManager.LOG_TYPE_FOOD)

        this.checkin.postValue(checkin)
    }

    companion object {

        const val TOTAL_FAT = "totalFat"
        const val PROTEIN = "protein"
        const val TOTAL_CARBS = "totalCarbs"
        const val DIETARY_FIBER = "dietaryFiber"
        const val CALORIES = "calories"

    }

}