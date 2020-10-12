package com.azumio.android.foodlenslibrary.adapter

import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory
import com.azumio.android.foodlenslibrary.model.SegmentResponse


open class ResultListHeaderItem(override val type:ResultListItemType = ResultListItemType.HEADER,val item: List<FoodSuggestionCategory>): ResultListBaseItem(type) {

}

open class ResultListFooterItem(override val type:ResultListItemType = ResultListItemType.FOOTER,val title:String? = null): ResultListBaseItem(type) {

}