package com.azumio.android.foodlenslibrary.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.activity.AddFoodActivity;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;
import com.azumio.android.foodlenslibrary.utils.DialogUtils;
import com.azumio.android.foodlenslibrary.utils.PremiumStatus;
import com.azumio.android.foodlenslibrary.utils.UserProfile;
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.UUID;

public class SearchQuickFragment extends BaseFragment implements OnSearchListener, OnSaveListener, View.OnClickListener {
    private static final String LOG_TAG = SearchQuickFragment.class.getSimpleName();

    EditText mName;
    EditText mCalories;
    EditText mCarb;
    EditText mProteins;
    EditText mFat;
    CenteredCustomFontView mCarbohydrates;
    CenteredCustomFontView mProteinValue;
    CenteredCustomFontView mFatValue;
    private View mView;
    private FoodSearchData mDataQuickItems;

    private DialogUtils dialogUtils;

    public static SearchQuickFragment newInstance() {
        return new SearchQuickFragment();
    }

    public void setDataQuickItems(FoodSearchData dataQuickItems) {
        this.mDataQuickItems = dataQuickItems;
    }

    public FoodSearchData getDataQuickItems() {
        return mDataQuickItems;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_quick_search, null);
        mName = mView.findViewById(R.id.cell_name);
        mCalories = mView.findViewById(R.id.cell_calories);
        mCarb = mView.findViewById(R.id.cell_carbohydrates);
        mProteins = mView.findViewById(R.id.cell_protein);
        mFat = mView.findViewById(R.id.cell_fat);
        mCarbohydrates = mView.findViewById(R.id.cell_carb);
        mProteinValue = mView.findViewById(R.id.cell_prot);
        mFatValue = mView.findViewById(R.id.fat);

        dialogUtils = new DialogUtils(getActivity());
        mCarbohydrates.setOnClickListener(this);
        mProteinValue.setOnClickListener(this);
        mFatValue.setOnClickListener(this);

        TextView addBtn = (TextView) mView.findViewById(R.id.addbtn);
        addBtn.setOnClickListener(view ->
        {
            FoodSearchData mapFood = new FoodSearchData();
            HashMap<String, Double> mapNutrition = new HashMap<>();

            mapFood.setType(CaloriesManager.LOG_TYPE_QUICK);
            SegmentResponse.FoodItem.ServingSize servingSizeData = new SegmentResponse.FoodItem.ServingSize(Double.valueOf(CaloriesManager.CALORIES_WEIGHT), CaloriesManager.CALORIES_UNIT);

            mapFood.setServingSize(servingSizeData);
            String uniqueId = UUID.randomUUID().toString();
            mapFood.setId(uniqueId);

            if (mName.getText().length() < 1) {
                mapFood.setName(getString(R.string.quick_calories));
            } else {
                mapFood.setName(mName.getText().toString());
            }
            if (mCalories.getText().length() < 1) {
                dialogUtils.showAlertDialog(getString(R.string.add_calories), getParent());
                return;
            } else {
                mapNutrition.put(CaloriesManager.CALORIES, Double.valueOf(mCalories.getText().toString()));
            }
            if (mCarb.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.TOTAL_CARBS, Double.valueOf(mCarb.getText().toString()) / 1000.0f);
            }
            if (mProteins.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.PROTEIN, Double.valueOf(mProteins.getText().toString()) / 1000.0f);
            }

            if (mFat.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.TOTAL_FAT, Double.valueOf(mFat.getText().toString()) / 1000.0f);
            }


            mapFood.setNutritionFromMap(mapNutrition);
            ((AddFoodActivity) getActivity()).addToSelectedList(mapFood);
            //CleverTapEventsLogger cleverTapEventsHelper = new CleverTapEventsLogger();
            //cleverTapEventsHelper.logEvent(CleverTapEventsLogger.CALORIES_QUICK_ADD);
            mName.setText("");
            mCalories.setText("");
            mCarb.setText("");
            mProteins.setText("");
            mFat.setText("");
        });

        if (mDataQuickItems != null) {
            addBtn.setVisibility(View.GONE);
            mName.setText(mDataQuickItems.getName());
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMaximumFractionDigits(1);
            if (mDataQuickItems.getNutrition().getCalories() > 0) {
                mCalories.setText(nf.format(Double.valueOf(mDataQuickItems.getNutrition().getCalories())));
            }
            if (mDataQuickItems.getNutrition().getTotalCarbs() > 0) {
                mCarb.setText(nf.format((mDataQuickItems.getNutrition().getTotalCarbs()) * 1000.0));
            }
            if (mDataQuickItems.getNutrition().getProtein() > 0) {
                mProteins.setText(nf.format((mDataQuickItems.getNutrition().getProtein()) * 1000.0));
            }
            if (mDataQuickItems.getNutrition().getTotalFat() > 0) {
                mFat.setText(nf.format((mDataQuickItems.getNutrition().getTotalFat()) * 1000.0));
            }
        }

        viewControls();
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mCalories.requestFocus();
        int textLength = mCalories.getText().length();
        mCalories.setSelection(textLength);
        //	onUpdatePremiumStatus(isPremium());
        onUpdatePremiumStatus(PremiumStatus.isPremium(UserProfile.Companion.getPremiumStatus()));
    }

    private void viewControls() {
        mCarb.setVisibility(View.GONE);
        mProteins.setVisibility(View.GONE);
        mFat.setVisibility(View.GONE);
        mCarbohydrates.setVisibility(View.GONE);
        mProteinValue.setVisibility(View.GONE);
        mFatValue.setVisibility(View.GONE);
    }

    public void onUpdatePremiumStatus(boolean isPremium) {
        viewControls();
        if (isPremium) {
            mCarb.setVisibility(View.VISIBLE);
            mProteins.setVisibility(View.VISIBLE);
            mFat.setVisibility(View.VISIBLE);
        } else {
            mCarbohydrates.setVisibility(View.VISIBLE);
            mProteinValue.setVisibility(View.VISIBLE);
            mFatValue.setVisibility(View.VISIBLE);
            mCarbohydrates.setText(ArgusIconMap.getInstance().get(ArgusIconMap.PREMIUM));
            mProteinValue.setText(ArgusIconMap.getInstance().get(ArgusIconMap.PREMIUM));
            mFatValue.setText(ArgusIconMap.getInstance().get(ArgusIconMap.PREMIUM));
        }
    }

    @Override
    public void onSearchTextChange(String text) {

    }

    @Override
    public void onSearchTextDone(String text) {

    }

    @Override
    public void onMealTypeChanged(String type) {

    }

    @Override
    public void onViewVisibility(boolean visible) {
        if (visible) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
        //	onUpdatePremiumStatus(isPremium());
        onUpdatePremiumStatus(PremiumStatus.isPremium(UserProfile.Companion.getPremiumStatus()));
    }

    @Override
    public void onRemovedItem(FoodSearchData foodItem) {

    }

    @Override
    public void onRefreshPremiumStatus() {
        //	onUpdatePremiumStatus(isPremium());
        onUpdatePremiumStatus(PremiumStatus.isPremium(UserProfile.Companion.getPremiumStatus()));
    }

    @Override
    public void clearSearch(String text) {

    }

    @Override
    public void save(TextView save, ProgressBar progressBar) {
        Intent intent = new Intent();

        try {
            HashMap<String, Double> mapNutrition = new HashMap<>();
            if (mCalories.getText().length() < 1) {
                dialogUtils.showAlertDialog(getString(R.string.add_calories), getParent());
                return;
            } else {
                mapNutrition.put(CaloriesManager.CALORIES, Double.valueOf(mCalories.getText().toString()));
            }

            if (mCarb.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.TOTAL_CARBS, Double.valueOf(mCarb.getText().toString()) / 1000.0f);
            }
            if (mProteins.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.PROTEIN, Double.valueOf(mProteins.getText().toString()) / 1000.0f);
            }

            if (mFat.getText().length() > 0) {
                mapNutrition.put(CaloriesManager.TOTAL_FAT, Double.valueOf(mFat.getText().toString()) / 1000.0f);
            }
            mDataQuickItems.setNutritionFromMap(mapNutrition);
            mDataQuickItems.setName(mName.getText().toString());


            Gson gson = new Gson();
            intent.putExtra(CaloriesManager.PROPERTY_DATA, gson.toJson(mDataQuickItems));
            getParent().setResult(Activity.RESULT_OK, intent);
            getParent().finish();
        } catch (Exception e) {
            Log.e(LOG_TAG, "JsonProcessingException  while saving data ", e);
        }
    }

    @Override
    public void onClick(View view) {
		/*
		switch ( view.getId() )
		{
			case R.id.cell_carb:
			case R.id.cell_prot:
			case R.id.fat:

				cleverTapEventsHelper.logPremiumPageEvents(CleverTapEventsLogger.PREMIUM_PAGE_VIEWED_EVENT, "Calories Quick Search");
				if (BuildConfig.APP_KEY.equalsIgnoreCase(AppKeys.ARGUS_APP_KEY) || BuildConfig.APP_KEY.equalsIgnoreCase(AppKeys.FITNESS_BUDDY_APP_KEY) || BuildConfig.APP_KEY.equalsIgnoreCase(AppKeys.FITNESS_BUDDY_PLUS_APP_KEY))
				{
					PremiumPurchaseActivity.start(getContext());
				}
				else
				{
					PremiumScreenActivity.start(getActivity(), "Quick-add-macros");
				}
				//	CaloriesManager.showAlertDialog(getString(R.string.quick_add_macros), getString(R.string.upgrade_quick_calories), getActivity(), SearchQuickFragment.class.getName(), "Quick-add-macros");
		}
		 */
    }
}
