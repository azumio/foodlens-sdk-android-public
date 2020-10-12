package com.azumio.android.foodlenslibrary.model

import com.google.gson.annotations.SerializedName

data class FoodSearchResponse(@SerializedName("results")
                                  val results: List<FoodSearchData>)