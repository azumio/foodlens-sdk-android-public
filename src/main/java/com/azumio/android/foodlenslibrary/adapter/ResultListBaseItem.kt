package com.azumio.android.foodlenslibrary.adapter

import com.azumio.android.foodlenslibrary.model.SegmentResponse

enum class ResultListItemType(value: Int) {
    HEADER(0),
    ITEM(1),
    SELECTEDITEM(2),
    FOOTER(3),
}

open class ResultListBaseItem(open val type: ResultListItemType) {

}