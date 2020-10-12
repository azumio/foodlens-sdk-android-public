package com.azumio.android.foodlenslibrary.adapter

import com.azumio.android.foodlenslibrary.model.FoodLog
import com.azumio.android.foodlenslibrary.model.FoodSuggestion
import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory

open class ResultListSelectedFoodItem(override val type:ResultListItemType = ResultListItemType.SELECTEDITEM, val item: FoodLog): ResultListBaseItem(type) {

}