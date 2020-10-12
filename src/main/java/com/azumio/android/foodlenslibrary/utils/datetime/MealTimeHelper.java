package com.azumio.android.foodlenslibrary.utils.datetime;


import java.util.Date;

public class MealTimeHelper
{
	public static final int MEAL_BREAKFAST = 0;
	public static final int MEAL_LUNCH = 1;
	public static final int MEAL_DINNER = 2;
	public static final int MEAL_SNACK = 3;

	public static int calculateCurrentMealByTime()
	{
	//	DateTime now = new DateTime();
		int hour = new Date().getHours();

		if (hour > 3 && hour < 11)
		{
			return MEAL_BREAKFAST;
		}
		else if (hour >= 11 && hour < 16)
		{
			return MEAL_LUNCH;
		}
		else if (hour >= 16 && hour < 23)
		{
			return MEAL_DINNER;
		}

		return MEAL_SNACK;
	}

	public static String getMealLabel(int currentMeal)
	{
		switch ( currentMeal )
		{
			case MEAL_BREAKFAST:
				return "breakfast";
			case MEAL_DINNER:
				return "dinner";
			case MEAL_LUNCH:
				return "lunch";
			case MEAL_SNACK:
				return "snack";
		}

		//default one
		return "snack";
	}

	public static String getMealLabelByTimeOfDay(){
		int meal = calculateCurrentMealByTime();
		return getMealLabel(meal);
	}
}
