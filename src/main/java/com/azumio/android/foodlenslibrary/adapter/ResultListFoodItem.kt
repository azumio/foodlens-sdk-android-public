package com.azumio.android.foodlenslibrary.adapter

import com.azumio.android.foodlenslibrary.model.FoodSuggestion
import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory
import com.azumio.android.foodlenslibrary.model.SegmentResponse

open class ResultListFoodItem(override val type:ResultListItemType = ResultListItemType.ITEM,val item: FoodSuggestion,val category:FoodSuggestionCategory): ResultListBaseItem(type) {

}