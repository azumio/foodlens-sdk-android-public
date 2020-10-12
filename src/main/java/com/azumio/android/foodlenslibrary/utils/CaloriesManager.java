package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.TextView;

import com.azumio.android.foodlenslibrary.model.CaloriesInfoData;
import com.azumio.android.foodlenslibrary.model.CaloriesNutritionModel;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CaloriesManager {
    public static String CALORIES_UNIT = "1 serving";
    public static String CALORIES_WEIGHT = "1";
    public static final Double SERVING_WEIGHT = 1.0d;
    public static Double NUMBER_OF_SERVINGS = 1.0d;
    public static final String MEAL_TYPE_BREAKFAST = "breakfast";
    public static final String MEAL_TYPE_LUNCH = "lunch";
    public static final String MEAL_TYPE_DINNER = "dinner";
    public static final String MEAL_TYPE_SNACK = "snack";
    public static final String LOG_TYPE_RECIPE = "recipe";
    public static final String LOG_TYPE_FOOD = "food";
    public static final String LOG_TYPE_FOOD_NAME = "foodname";
    public static final String LOG_TYPE_QUICK = "quick";
    public static String CALORIES = "calories";
    public static String TOTAL_CARBS = "totalCarbs";
    public static String PROTEIN = "protein";
    public static String TOTAL_FAT = "totalFat";
   public static String PROPERTY_DATA = "data";
    public static String PROPERTY_ID = "id";
    public static String PROPERTY_REMOTE_ID = "remoteid";
    public static String PROPERTY_TYPE = "type";
    private static final String LOG_TAG = CaloriesManager.class.getSimpleName();
    public static  String PROPERTY_SERVING_SIZE = "servingSize";
    public static  String PROPERTY_SERVING_SIZES = "servingSizes";
    public static  String PROPERTY_SERVING_WEIGHT = "servingWeight";
    public static  String PROPERTY_UNIT = "unit";
    public static String PROPERTY_NO_OF_SERVINGS = "numberOfServings";
    public static final String MEAL_ORDER[] = {MEAL_TYPE_BREAKFAST, MEAL_TYPE_LUNCH, MEAL_TYPE_DINNER, MEAL_TYPE_SNACK};
    public static String ITEMS = "items";
    public static String ITEM = "item";
    public static String PROPERTY_CALORIES = "calories";
    public static String SAVE = "save";
    static CaloriesNutritionModel nutritionData;
    public static HashMap<String, Double> getNutritionSummation(List<FoodSearchData> data) {
        HashMap<String, Double> mapNutrition = new HashMap<>();
        for (FoodSearchData mealMap : data) {
            if (mealMap.getNutrition() != null) {
                Double servingWeight = FoodSearchData.DEFAULT_SERVING_WEIGHT;
                Double numberOfServings = NUMBER_OF_SERVINGS;
                if (mealMap.getServingSize() != null) {
                    if (mealMap.getServingSize().getServingWeight() > 0) {
                        servingWeight = Double
                                .valueOf(mealMap.getServingSize().getServingWeight() );
                    }
                } else if (mealMap.getServingSizes() != null) {
                    if (mealMap.getServingSizes().get(0).getServingWeight() > 0) {
                        servingWeight = Double.valueOf(mealMap.getServingSizes().get(0).getServingWeight());
                    }
                }

                if (mealMap.getNumberOfServings() > 0) {
                    numberOfServings = mealMap.getNumberOfServings();
                }

                Map<String, Double> dNutrition = new HashMap<>(mealMap.getNutritionAsMap());

                for (String mkey : dNutrition.keySet()) {
                    CaloriesInfoData dFile = getCaloriesNutritionDataForKey(mkey);

                    if (dFile != null) {
                        Double returnValue = getCaloriesInfoData(dFile, dNutrition, mkey, numberOfServings, servingWeight);

                        if (mkey.equalsIgnoreCase(CaloriesManager.PROPERTY_CALORIES)) {
                            returnValue = Double.valueOf(Math.round(returnValue));
                        }

                        dNutrition.put(mkey, returnValue);

                        if (mapNutrition.get(mkey) != null) {
                            Double val = Double.valueOf(mapNutrition.get(mkey));
                            Double value = (val + returnValue);
                            mapNutrition.put(mkey, value);
                        } else {
                            mapNutrition.put(mkey, returnValue);
                        }
                    } else {
                        Double val2 = Double.valueOf(dNutrition.get(mkey));
                        if (mapNutrition.get(mkey) != null) {
                            Double val = Double.valueOf(mapNutrition.get(mkey));
                            Double value = val + (val2 * servingWeight * numberOfServings);
                            mapNutrition.put(mkey, value);
                        } else {
                            Double numberofserv = val2 * servingWeight * numberOfServings;
                            mapNutrition.put(mkey, numberofserv);
                        }
                    }
                }
            }
        }
        return mapNutrition;
    }

    public static void formatCaloriesLabel(Map<String, Double> mealDictionary, TextView lblTotalCalories, TextView lblTotalCarbs, TextView lblTotalFats, TextView lblTotalProteins) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(0);
        CaloriesManager.setLabel(mealDictionary, nf, lblTotalCalories, CaloriesManager.CALORIES);

        nf.setMaximumFractionDigits(1);
        CaloriesManager.setLabel(mealDictionary, nf, lblTotalCarbs, CaloriesManager.TOTAL_CARBS);
        CaloriesManager.setLabel(mealDictionary, nf, lblTotalFats, CaloriesManager.TOTAL_FAT);
        CaloriesManager.setLabel(mealDictionary, nf, lblTotalProteins, CaloriesManager.PROTEIN);
    }
    public static void setLabel(Map<String, Double> mealDictionary, NumberFormat nf, TextView label, String key) {
        if (mealDictionary.get(key) != null) {
            Double value = Double.valueOf(mealDictionary.get(key));
            if (key.equalsIgnoreCase(CaloriesManager.CALORIES)) {
                label.setText(String.format("%s ", nf.format(value)));
            } else {
                label.setText(String.format("%s g", nf.format(value)));
            }
        } else {
            label.setText("-");
        }
    }

    public static Double getCaloriesInfoData(CaloriesInfoData dFile, Map<String, Double> dNutrition, String mkey, Double numberOfServings, Double servingWeight) {
        Double val2 = Double.valueOf(dNutrition.get(mkey));
        Double x = val2 * numberOfServings;
        x = x * servingWeight;

        if (dFile.getUnit().equalsIgnoreCase("g")) {
            x = x * 1000;
        } else if (dFile.getUnit().equalsIgnoreCase("mg")) {
            x = x * 1000000;
        } else if (dFile.getUnit().equalsIgnoreCase("%")) {
            if (dFile.getDrv() != null) {
                Double dailyValue = Double.valueOf(dFile.getDrv());
                x = (x / dailyValue) * 100;
            }
        }
        return x;
    }


    public static Double getCaloriesInfoData(Map<String, Number> dNutrition, String mkey) {
        Double val2 = dNutrition.get(mkey).doubleValue();
        return val2;
    }

    public static Double getCaloriesInfoData(Double value, CaloriesInfoData map) {
        if (map.getUnit().equalsIgnoreCase("g")) {
            value = value / 1000.0f;
        }
        if (map.getUnit().equalsIgnoreCase("mg")) {
            value = value / 1000000.0f;
        } else if (map.getUnit().equalsIgnoreCase("%")) {
            if (map.getDrv() != null) {
                double dailyValue = Double.valueOf(map.getDrv());
                value = (value / 100) * dailyValue;
            }
        }
        return value;
    }

    static CaloriesInfoData getCaloriesNutritionDataForKey(String key) {
        Map<String, CaloriesInfoData> map = null;
        if (nutritionData == null) {
            if (readNutritionData(ApplicationContextProvider.getApplicationContext()) != null) {
                map = readNutritionData(ApplicationContextProvider.getApplicationContext()).getInfo();
            }
        } else {
            map = nutritionData.getInfo();
        }

        if (map != null) {
            for (String data : map.keySet()) {
                if (data.equalsIgnoreCase(key)) {
                    return map.get(key);
                }
            }
        }
        return null;
    }

    public static CaloriesNutritionModel readNutritionData(Context context) {
        AssetManager mgr = context.getAssets();
        CaloriesNutritionModel result = null;
        try {
            Gson gson = new Gson();
            InputStream inputStream = mgr.open("nutritionalInfo.json");
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int rs = bis.read();
            while(rs != -1) {
                buf.write((byte) rs);
                rs = bis.read();
            }
            String json = buf.toString("UTF-8");
            result = gson.fromJson(json,CaloriesNutritionModel.class);
            nutritionData = result;
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException while reading values from nutritionalInfo.json ", e);
        }
        return result;
    }
}
