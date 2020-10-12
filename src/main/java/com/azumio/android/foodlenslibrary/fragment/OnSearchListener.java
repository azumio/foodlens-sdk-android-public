package com.azumio.android.foodlenslibrary.fragment;


import com.azumio.android.foodlenslibrary.model.FoodSearchData;

public interface OnSearchListener
{
	void onSearchTextChange(String text);

	void onSearchTextDone(String text);

	void onMealTypeChanged(String type);

	void onViewVisibility(boolean isVisible);

	void onRemovedItem(FoodSearchData foodItem);

	void onRefreshPremiumStatus();

	void clearSearch(String text);
}


