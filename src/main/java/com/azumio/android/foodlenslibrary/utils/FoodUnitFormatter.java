package com.azumio.android.foodlenslibrary.utils;

import android.text.TextUtils;
import android.widget.TextView;

import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class FoodUnitFormatter {

    public static void formatFoodUnit(FoodSearchData foodItem, TextView lunchDetailsText)
    {

        StringBuilder lunchDetails = new StringBuilder();
        if (foodItem.getBrand() != null)
        {
            lunchDetails.append(foodItem.getBrand());
            lunchDetails.append(", ");
        }

        String serving = "";
        if (foodItem.getServingSize() != null)
        {
            serving = foodItem.getServingSize().getUnit();
            if(serving != null)
            {
                if (serving.length() < 1)
                {
                    serving = CaloriesManager.CALORIES_UNIT;
                }
            }
            else
            {
                serving = "";
            }
        }
        else if (foodItem.getServingSizes() != null)
        {
            if (foodItem.getServingSizes().size() > 0)
            {
                serving = foodItem.getServingSizes().get(0).getUnit();
            }
            if(serving == null)
            {
                serving = "";
            }
        }

        NumberFormat nf = NumberFormatUtils.newInstance();
        if (foodItem.getNumberOfServings() > 0)
        {
            Double noOfServing = foodItem.getNumberOfServings();
            boolean parsed = false;
            if(serving != null && serving.length() > 0)
            {
                String[] arr = serving.split(" ");
                if (arr.length > 1)
                {
                    Double n = isNumeric(arr[0]);
                    if (n != Double.POSITIVE_INFINITY)
                    {
                        arr[0] = nf.format(n * noOfServing);
                        serving = TextUtils.join(" ", arr);
                        parsed = true;
                        lunchDetails.append(serving);
                    }
                }
            }
            if (!parsed)
            {
                lunchDetails.append(nf.format(noOfServing));
                lunchDetails.append(serving);
            }
        }
        else
        {
            lunchDetails.append(serving);
        }


        lunchDetailsText.setText(lunchDetails);
    }



    public static void formatCaloriesAndVisibility(FoodSearchData foodItem, TextView recentCalories)
    {
        String calories = "";

        NumberFormat nf = NumberFormatUtils.newInstance();
        SegmentResponse.FoodItem.Nutrition nutritionInfo = null;

        if (foodItem.getNutrition() != null)
        {
            nutritionInfo = foodItem.getNutrition();
        }

        if (nutritionInfo != null)
        {
            if (foodItem.getServingSize() != null)
            {
                Double servingWeight = 0.0;
                if (foodItem.getServingSize().getServingWeight() >0)
                {

                    servingWeight = Double.valueOf(foodItem.getServingSize().getServingWeight());

                }
                else
                {
                    servingWeight = CaloriesManager.SERVING_WEIGHT;
                }

                Double ns = CaloriesManager.NUMBER_OF_SERVINGS;
                if (foodItem.getNumberOfServings() > 0)
                {
                    ns = foodItem.getNumberOfServings();
                }

                if (nutritionInfo.getCalories() > 0)
                {
                    Double cal = Double.valueOf(nutritionInfo.getCalories()) * servingWeight * ns;
                    nf.setMaximumFractionDigits(0);
                    calories = nf.format(cal);
                }
                else
                {
                    calories = "";
                }
            }
        }
        else if (foodItem.getCalories() > 0)
        {
            if (foodItem.getServingSize() != null)
            {
                Double servingWeight = 0.0;
                if (foodItem.getServingSize().getServingWeight() > 0)
                {
                    servingWeight = Double.valueOf(foodItem.getServingSize().getServingWeight());
                }
                else
                {
                    servingWeight = CaloriesManager.SERVING_WEIGHT;
                }

                Double cal = Double.valueOf(foodItem.getCalories()) * servingWeight;
                nf.setMaximumFractionDigits(0);
                calories = nf.format(cal);
            }
        }
        else
        {
            calories = "";
        }


        if (TextUtils.isEmpty(calories) && foodItem.getNutrition() != null)
        {
            if (foodItem.getNutrition().getCalories() > 0)
            {
                Double cal = Double.valueOf(foodItem.getNutrition().getCalories());
                nf.setMaximumFractionDigits(0);
                calories = nf.format(cal);
            }
        }


        final String caloriesLabel = TextUtils.isEmpty(calories) ? "" : String.format("%s", calories);
        recentCalories.setText(caloriesLabel);
    }


    public static String formatFoodUnit(SegmentResponse.FoodItem foodItem)
    {

        StringBuilder lunchDetails = new StringBuilder();
//        if (foodItem.getBrand() != null)
//        {
//            lunchDetails.append(foodItem.getBrand());
//            lunchDetails.append(", ");
//        }

        String serving = "";
        if (foodItem.getServingSize() != null)
        {
            serving = foodItem.getServingSize().getUnit();
            if(serving != null)
            {
                if (serving.length() < 1)
                {
                    serving = CaloriesManager.CALORIES_UNIT;
                }
            }
            else
            {
                serving = "";
            }
        }
        else if (foodItem.getServingSizes() != null)
        {
            if (foodItem.getServingSizes().size() > 0)
            {
                serving = foodItem.getServingSizes().get(0).getUnit();
            }
            if(serving == null)
            {
                serving = "";
            }
        }

        NumberFormat nf = NumberFormatUtils.newInstance();
        if (foodItem.getNumberOfServings() > 0)
        {
            Double noOfServing = foodItem.getNumberOfServings();
            boolean parsed = false;
            if(serving != null && serving.length() > 0)
            {
                String[] arr = serving.split(" ");
                if (arr.length > 1)
                {
                    Double n = isNumeric(arr[0]);
                    if (n != Double.POSITIVE_INFINITY)
                    {
                        arr[0] = nf.format(n * noOfServing);
                        serving = TextUtils.join(" ", arr);
                        parsed = true;
                        lunchDetails.append(serving);
                    }
                }
            }
            if (!parsed)
            {
                lunchDetails.append(nf.format(noOfServing));
                lunchDetails.append(serving);
            }
        }
        else
        {
            lunchDetails.append(serving);
        }

        return lunchDetails.toString();
    }

    private static double isNumeric(String value)
    {
        double Value;
        try
        {
            Value = Double.parseDouble(value);
        }
        catch (NumberFormatException nfe)
        {
            return Double.POSITIVE_INFINITY;
        }
        return Value;
    }

    public static String formatCalories(SegmentResponse.FoodItem foodItem)
    {
        String calories = "";

        NumberFormat nf = NumberFormatUtils.newInstance();
        SegmentResponse.FoodItem.Nutrition nutritionInfo = null;

        if (foodItem.getNutrition() != null)
        {
            nutritionInfo = foodItem.getNutrition();
        }

        if (nutritionInfo != null)
        {
            if (foodItem.getServingSize() != null)
            {
                Double servingWeight = 0.0;
                if (foodItem.getServingSize().getServingWeight() >0)
                {

                    servingWeight = Double.valueOf(foodItem.getServingSize().getServingWeight());

                }
                else
                {
                    servingWeight = CaloriesManager.SERVING_WEIGHT;
                }

                Double ns = CaloriesManager.NUMBER_OF_SERVINGS;
                if (foodItem.getNumberOfServings() > 0)
                {
                    ns = foodItem.getNumberOfServings();
                }

                if (nutritionInfo.getCalories() > 0)
                {
                    Double cal = Double.valueOf(nutritionInfo.getCalories()) * servingWeight * ns;
                    nf.setMaximumFractionDigits(0);
                    calories = nf.format(cal);
                }
                else
                {
                    calories = "";
                }
            }
        }
        /*
        else if (foodItem.getCalories() != null)
        {
            if (foodItem.getServingSize() != null)
            {
                Double servingWeight = 0.0;
                if (foodItem.getServingSize().getServingWeight() != null)
                {
                    if (foodItem.getServingSize().getServingWeight().length() > 0)
                    {
                        servingWeight = Double.valueOf(foodItem.getServingSize().getServingWeight());
                    }
                }
                else
                {
                    servingWeight = CaloriesManager.SERVING_WEIGHT;
                }

                Double cal = Double.valueOf(foodItem.getCalories()) * servingWeight;
                nf.setMaximumFractionDigits(0);
                calories = nf.format(cal);
            }
        }

         */
        else
        {
            calories = "";
        }


        if (TextUtils.isEmpty(calories) && foodItem.getNutrition() != null)
        {
            if (foodItem.getNutrition().getCalories()>0)
            {
                Double cal =foodItem.getNutrition().getCalories();
                nf.setMaximumFractionDigits(0);
                calories = nf.format(cal);
            }
        }


        final String caloriesLabel = TextUtils.isEmpty(calories) ? "" : String.format("%s", calories);
       return caloriesLabel;
    }
}
