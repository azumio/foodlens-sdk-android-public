package com.azumio.android.foodlenslibrary.viewModel

import android.graphics.Point
import android.graphics.PointF
import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.azumio.android.foodlenslibrary.FoodLens
import com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem
import com.azumio.android.foodlenslibrary.adapter.ResultListFoodItem
import com.azumio.android.foodlenslibrary.adapter.ResultListSelectedFoodItem
import com.azumio.android.foodlenslibrary.api.Resource
import com.azumio.android.foodlenslibrary.common.DataWrapper
import com.azumio.android.foodlenslibrary.core.FoodLensConfig
import com.azumio.android.foodlenslibrary.model.*
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import com.orhanobut.logger.Logger.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.InputStream
import java.util.*


class ResultViewModelModelFactory(private val uri: Uri) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(Uri::class.java)
            .newInstance(uri)
}

class ResultViewModel(val imageUri: Uri) : ViewModel() {
    // TODO: Implement the ViewModel


    enum class ItemState(value: Int) {
        LOGGED(0),
        EDITED(1),
        DELETED(2)
    }

    fun InputStream.toFile(path: String) {
        use { input ->
            File(path).outputStream().use { input.copyTo(it) }
        }
    }


    private lateinit var segmentResponse: SegmentResponse
    val isNotFood: MutableLiveData<Boolean> = MutableLiveData()
    val imageCacheId:String
    get() = segmentResponse.imagecacheId
    fun recognizeImage() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            val file = imageUri.toFile()
            val requestBody: RequestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
            val fileToUpload: MultipartBody.Part =
                MultipartBody.Part.createFormData("photo", file.name, requestBody)
            val response = FoodLens.lastAuthorizedInstance?.apiService?.getSegments(fileToUpload)

            response?.body()?.let {
                segmentResponse = it
            }

            val likelyFoodSegments = response?.body()?.foods?.filter { it.isFood && it.notFoodRatio <= FoodLensConfig.NonFoodRatioThreshold }
            if((likelyFoodSegments?.size ?: 0) <= 0)
            {
                withContext(Dispatchers.Main) {
                    isNotFood.value = true
                }
            }
            else {

                loadFoodSegments()
            }
            emit(Resource.success(data = true))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

    fun recognizeSegment(newSeg:FoodSegment) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            var segResponse: SegmentResponse? = null
            val points = String.format("[[%f,%f]]", newSeg?.center?.x ?: 0, newSeg?.center?.y ?: 0)
            val response =
                FoodLens.lastAuthorizedInstance?.apiService?.getSuggestions(points, imageCacheId)

            response?.body()?.let {
               // segResponse = it

               val filtered = it.foods.filter { food-> food.center == newSeg.center }
                if(filtered.isNotEmpty())
                {
                    it.foods = filtered
                    segResponse = it
                }
                else
                {
                    segResponse = it
                }


            }

            val likelyFoodSegments =
                segResponse?.foods?.filter { it.isFood && it.notFoodRatio <= FoodLensConfig.NonFoodRatioThreshold }
            if ((likelyFoodSegments?.size ?: 0) <= 0) {
                withContext(Dispatchers.Main) {
                   // isNotFood.value = true
                }
            } else {

                segResponse?.foods?.firstOrNull { it.isFood && it.notFoodRatio <= FoodLensConfig.NonFoodRatioThreshold }
                    ?.let { foods ->

                    newSeg?.categories = foods.categories.map { cat ->
                        FoodSuggestionCategory(
                            identifier = UUID.randomUUID().toString(),
                            group = cat.group,
                            suggestions = cat.items.map { item ->
                                FoodSuggestion(
                                    foodItem = item,
                                    identifier = item.id
                                )
                            })
                    }
                        newSegment.postValue(newSeg)
                }
            }
            emit(Resource.success(data = true))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }


    val foodSegments: MutableLiveData<List<FoodSegment>> = MutableLiveData()
    val selectedSegment:MutableLiveData<FoodSegment?> = MutableLiveData()
    val selectedItemState:MutableLiveData<ItemState> = MutableLiveData()
    val newSegment:MutableLiveData<FoodSegment?> = MutableLiveData()
    val allSegmentsDeleted:MutableLiveData<Boolean> = MutableLiveData()
    val segmentDeleted:MutableLiveData<FoodSegment?> = MutableLiveData()

    fun createNewSegment(center:SegmentResponse.TraceSegment.Center)
    {
        i("createNewSegment")
        val segment = FoodSegment(identifier = UUID.randomUUID().toString(),boundingBox = SegmentResponse.TraceSegment.BoundingBox(0.2,0.2,center.x,center.y),center = center,isFood = true,notFoodRatio = 0.0,categories = emptyList())
        newSegment.postValue(segment)
    }

    fun deleteSegment(segment: FoodSegment)
    {
        if(segment.identifier == newSegment.value?.identifier)
        {
            if(foodSegments.value?.firstOrNull { it.identifier == newSegment.value?.identifier } == null)
            {
                segmentDeleted.postValue(segment)
                newSegment.postValue(null)
            }
        }
        else
        {
            segmentDeleted.postValue(segment)
            var tempList= foodSegments.value as MutableList<FoodSegment>
            tempList.remove(segment)
            foodSegments.value = tempList
        }

        if(foodSegments.value?.size ?: 0 < 1)
        {
                allSegmentsDeleted.postValue(true)
        }
    }

    fun loadFoodSegments()
    {
        if (this::segmentResponse.isInitialized) {

            val likelyFoodSegments =
                segmentResponse.foods?.filter { it.isFood && it.notFoodRatio <= FoodLensConfig.NonFoodRatioThreshold }
                    .map {
                        FoodSegment(
                            identifier = UUID.randomUUID().toString(),
                            boundingBox = it.boundingBox,
                            center = it.center,
                            isFood = it.isFood,
                            notFoodRatio = it.notFoodRatio,
                            categories = it.categories.map { cat ->
                                FoodSuggestionCategory(
                                    identifier = UUID.randomUUID().toString(),
                                    group = cat.group,
                                    suggestions = cat.items.map { item ->
                                        FoodSuggestion(
                                            foodItem = item,

                                            identifier = item.id
                                        )
                                    })
                            })
                    }
            likelyFoodSegments.forEach {
                it.categories.forEach { cat->
                    cat.suggestions.forEach { sug ->
                        if(sug.foodItem.numberOfServings <= 0)
                        sug.foodItem.numberOfServings = CaloriesManager.NUMBER_OF_SERVINGS
                    }
                }
            }
            foodSegments.postValue(likelyFoodSegments)
        }
    }

    fun  servingSizeEdited(serving: SegmentResponse.FoodItem.ServingSize,
                           numberOfServings: Double,item:ResultListBaseItem)
    {

        when(item){
            is ResultListSelectedFoodItem -> {
                item.item.underlyingFoodLog.servingSize = serving
                item.item.underlyingFoodLog.numberOfServings = numberOfServings
                selectedItemState.postValue(ItemState.EDITED)
            }
            is ResultListFoodItem -> {

                val foodItem = item.item.foodItem
                foodItem.servingSize = serving
                foodItem.numberOfServings = numberOfServings
                logItem(foodItem,item.category)
            }
        }

    }

    fun onSearchFood(wrapper: DataWrapper)
    {
        val list = wrapper.list
        val data = list.map {  SegmentResponse.FoodItem.initWithSearchData( FoodSearchData.initFromJson(it)) }
         data.forEach {
             logItem(it,FoodSuggestionCategory(UUID.randomUUID().toString(),it.group, emptyList()))
         }


    }

    fun logItem(foodItem: SegmentResponse.FoodItem, category: FoodSuggestionCategory)
    {
        selectedSegment.value?.foodLogs?.add(FoodLog(underlyingFoodLog = foodItem,suggestionId = selectedSegment?.value?.identifier ?: "" ,categoryId = category.identifier))
        selectedItemState.postValue(ItemState.LOGGED)

        if(selectedSegment.value?.identifier == newSegment.value?.identifier)
        {
            if(foodSegments.value?.firstOrNull { it.identifier == newSegment.value?.identifier } == null)
            {
                var tempList= foodSegments.value as MutableList<FoodSegment>
                tempList.add(newSegment.value!!)
                foodSegments.value = tempList
                newSegment.postValue(null)
            }
        }
    }

    fun removeLog(item: FoodLog)
    {
        foodSegments.value?.firstOrNull { it.identifier == item.suggestionId }?.let { seg ->
            seg.foodLogs.removeAll { it.categoryId == item.categoryId && it.underlyingFoodLog.id == item.underlyingFoodLog.id }
        }
        selectedItemState.postValue(ItemState.DELETED)

    }

    companion object {

        private const val TAG = "ResultViewModel"
    }

}

