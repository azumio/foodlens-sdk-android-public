package com.azumio.android.foodlenslibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.api.APIClient;
import com.azumio.android.foodlenslibrary.api.Continuation;
import com.azumio.android.foodlenslibrary.api.FoodLensService;
import com.azumio.android.foodlenslibrary.fragment.ServingDialog;
import com.azumio.android.foodlenslibrary.model.CaloriesInfoData;
import com.azumio.android.foodlenslibrary.model.CaloriesNutritionData;
import com.azumio.android.foodlenslibrary.model.CaloriesNutritionModel;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;
import com.azumio.android.foodlenslibrary.utils.ColorUtils;
import com.azumio.android.foodlenslibrary.utils.DialogUtils;
import com.azumio.android.foodlenslibrary.utils.NumberFormatUtils;
import com.azumio.android.foodlenslibrary.utils.UiUtils;
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView;
import com.azumio.android.foodlenslibrary.views.FillingView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Response;


import static com.azumio.android.foodlenslibrary.utils.ContextUtils.isNotFinishing;

public class EditEntryActivity extends BaseFragmentActivity {
    public static final int EDIT_ENTRY_CODE = 1013;
    private static final String LOG_TAG = EditEntryActivity.class.getSimpleName();
    private static final String TAG = "tag";
    private static int RESULT_CODE_EDITMODE = 1014;

    private HorizontalAdapter.ViewHolder mViewHolder;
    private FillingView mToolbar;
    private ExpandableListView mMainView;
    private CaloriesEditEntryAdapter mAdapter;
    private CaloriesNutritionModel mNutritionData;
    private FoodSearchData mCalorieData;
    private Double mCurrentNumberOfServing = CaloriesManager.NUMBER_OF_SERVINGS;
    private String mCurrentServingUnit;
    private Double mCurrentServingWeight;
    private String mMealType;
    private TextView mLblTotalCalories, mLblTotalCarbs, mLblTotalFats, mLblTotalProteins;
    private Map<String, Double> mMealDictionary;
    private EditText mNumberServingCount = null;

    private TextView mTextServingcount;
    private String servingValue = "";
    private boolean isfractionClick = false;
    private TextView downarrow;
    private boolean groupExpanded = true;
    private CenteredCustomFontView mAdd;
    private int selected_position = 0;
    private DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UiUtils.setupFullscreen(this);
        setContentView(R.layout.activity_edit_calorie_entry);
        mToolbar = findViewById(R.id.main_menu_toolbars);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.calories_color));
        ColorUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.calories_statusbar_color), ContextCompat.getColor(this, R.color.calories_color));
        initBackArrow();
        dialogUtils = new DialogUtils(this);
        mLblTotalCalories = findViewById(R.id.lblTotalCalories);
        mLblTotalCarbs = findViewById(R.id.lblTotalCarbs);
        mLblTotalFats = findViewById(R.id.lblTotalFat);
        mLblTotalProteins = findViewById(R.id.lblTotalProtien);
        mMainView = findViewById(R.id.recent_list_view);
        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.calories_footer_layout, null, false);
        mMainView.addFooterView(footerView);
        mAdd = mToolbar.findViewById(R.id.toolbar_done);

        Bundle extras = getIntent().getExtras();
        if (extras.getString(CaloriesManager.PROPERTY_DATA) != null) {
            FoodSearchData dataItem;
            try {
                dataItem =  FoodSearchData.Companion.initFromJson(extras.getString(CaloriesManager.PROPERTY_DATA));
                mCalorieData = dataItem;
                if (mCalorieData.getServingSizes() == null) {
                    SegmentResponse.FoodItem.ServingSize servingSize = new SegmentResponse.FoodItem.ServingSize(1.0,"1 Serving");

                    //mCalorieData.setServingSize(servingSize);
                    mCalorieData.setServingSizes(Arrays.asList(servingSize));
                }
                if (dataItem.getNumberOfServings() >0) {
                    mCurrentNumberOfServing = dataItem.getNumberOfServings();
                    if (dataItem.getServingSize() != null) {
                        if (dataItem.getServingSize().getUnit() != null) {
                            mCurrentServingUnit = dataItem.getServingSize().getUnit();
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "IOException while oncreate ", e);
            }
        }

        mAdd.setOnClickListener(view ->
        {
            if (mCurrentNumberOfServing == null) {
                dialogUtils.showAlertDialog(getString(R.string.add_servings), EditEntryActivity.this);
                return;
            } else if (mCurrentNumberOfServing == 0) {
                dialogUtils.showAlertDialog(getString(R.string.add_valid_servings), EditEntryActivity.this);
                return;
            }
            Intent intent = new Intent();

            try {
                //remove the serving weight information which was added for display purpose only.
                if (mCalorieData != null) {
                    if (mCalorieData.getNutrition() != null) {
                        HashMap<String, Double> map = new HashMap<>(mCalorieData.getNutritionAsMap());
                        map.remove(CaloriesManager.PROPERTY_SERVING_WEIGHT);
                        mCalorieData.setNutritionFromMap(map);
                    }
                    intent.putExtra(CaloriesManager.PROPERTY_DATA, mCalorieData.jsonString());
                    intent.putExtra(CaloriesManager.PROPERTY_UNIT, mCurrentServingUnit);
                    intent.putExtra(CaloriesManager.PROPERTY_SERVING_WEIGHT, String.valueOf(mCurrentServingWeight == null ? 1 : mCurrentServingWeight));
                    intent.putExtra(CaloriesManager.PROPERTY_NO_OF_SERVINGS, String.valueOf(mCurrentNumberOfServing == null ? 1 : mCurrentNumberOfServing));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "JsonProcessingException while listview click", e);
            }
        });
        mNutritionData = CaloriesManager.readNutritionData(this);
    }

    private void initBackArrow() {
        CenteredCustomFontView arrow = mToolbar.findViewById(R.id.activity_with_fragment_arrow);
        arrow.setText(ArgusIconMap.getInstance().get(ArgusIconMap.ARROW_LEFT));
        arrow.setOnClickListener(view -> finish());
    }

    private void refreshData() {
        if (mCalorieData != null) {
            if (mCalorieData.getServingSizes() != null) {
                List<SegmentResponse.FoodItem.ServingSize> servingSizes = mCalorieData.getServingSizes();
                if (mCurrentServingUnit == null) {
                    if (servingSizes.size() > 0) {
                        SegmentResponse.FoodItem.ServingSize d = servingSizes.get(0);
                        if (d.getServingWeight() > 0) {
                            mCurrentServingWeight = Double.valueOf(d.getServingWeight());
                        }
                        if (d.getUnit() != null) {
                            mCurrentServingUnit = d.getUnit();
                        }
                        if (mCalorieData.getNumberOfServings() > 0) {
                            mCurrentNumberOfServing = mCalorieData.getNumberOfServings();
                        }
                    }
                } else {
                    for (SegmentResponse.FoodItem.ServingSize servingSizeData : servingSizes) {
                        if (servingSizeData.getUnit() != null) {
                            if (servingSizeData.getUnit().equalsIgnoreCase(mCurrentServingUnit)) {
                                if (servingSizeData.getServingWeight() > 0) {
                                    mCurrentServingWeight = Double.valueOf(servingSizeData.getServingWeight());
                                }
                            }
                        }
                    }
                }
            }
            reloadNutritionDic(mCurrentServingWeight, mCurrentNumberOfServing);
        }


        mAdapter = new CaloriesEditEntryAdapter();
        mMainView.setAdapter(mAdapter);
        mMainView.expandGroup(0);
        mMainView.expandGroup(1);

        mMainView.setOnGroupClickListener((parent, v, groupPosition, id) ->
        {
            if (groupPosition == 0) {
                downarrow.setVisibility(View.GONE);
                return true;
            } else if (parent.isGroupExpanded(groupPosition)) {
                if (downarrow != null) {
                    downarrow.setVisibility(View.VISIBLE);
                }
                groupExpanded = false;
            } else {
                if (downarrow != null) {
                    downarrow.setVisibility(View.GONE);
                }
                groupExpanded = true;
            }
            return false;
        });


        if (mCalorieData != null) {
            mMainView.setOnChildClickListener((expandableListView, view, groupPosition, childPosition, l) ->
            {
                if (groupPosition == 0) {
                    ServingDialog dialog = new ServingDialog();
                    List<SegmentResponse.FoodItem.ServingSize> sizeDatas = mCalorieData.getServingSizes();
                    dialog.setServingSizeDataList(sizeDatas);
                    dialog.setSelectedNumberOfServing(mCurrentNumberOfServing);
                    dialog.setSelectedServingUnit(mCurrentServingUnit);
                    FragmentManager fm = getSupportFragmentManager();

                    dialog.setServingListner(new ServingDialog.OnServingInterface() {
                        @Override
                        public void servingSet(SegmentResponse.FoodItem.ServingSize servingSizeData, Double servingCount) {
                            if (servingSizeData.getServingWeight() > 0) {
                                mCurrentServingWeight = Double.valueOf(servingSizeData.getServingWeight());
                            }
                            mCurrentServingUnit = servingSizeData.getUnit();
                            mCurrentNumberOfServing = servingCount;
                            reloadNutritionDic(mCurrentServingWeight, mCurrentNumberOfServing);
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void servingCancel() {

                        }
                    });

                    dialog.show(fm, TAG);
                    return true;
                }
                return false;
            });
        }
    }

    void loadFoodInfoServer(String foodId, String type) {

        if (type.equalsIgnoreCase(CaloriesManager.LOG_TYPE_FOOD)) {

            final ProgressBar progressLayout = (ProgressBar) findViewById(R.id.progressView);
            progressLayout.setVisibility(View.VISIBLE);
            mAdd.setVisibility(View.GONE);
            FoodLensService apiService = APIClient.INSTANCE.createService(FoodLensService.class);
            BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO(), CoroutineStart.DEFAULT, (coroutineScope, continuation) ->
                    apiService.getFoodDetail(foodId, new Continuation<Response<FoodSearchData>>() {
                        @Override
                        public void resume(Response<FoodSearchData> value) {
                            if (isNotFinishing(EditEntryActivity.this)) {
                                EditEntryActivity.this.runOnUiThread(() -> {
                                    if (isNotFinishing(EditEntryActivity.this)) {
                                        mCalorieData = value.body();
                                        refreshData();
                                        progressLayout.setVisibility(View.GONE);
                                        mAdd.setVisibility(View.VISIBLE);
                                    }
                                });

                            }
                        }

                        @Override
                        public void resumeWithException(@NotNull Throwable exception) {
                            if (isNotFinishing(EditEntryActivity.this)) {
                                EditEntryActivity.this.runOnUiThread(() -> {
                                    progressLayout.setVisibility(View.GONE);
                                    mAdd.setVisibility(View.VISIBLE);
                                    Log.e(LOG_TAG, "Exception in failure response of CaloriesFoodDetailRequest ", exception);
                                });
                            }
                        }

                        @NotNull
                        @Override
                        public CoroutineContext getContext() {
                            return EmptyCoroutineContext.INSTANCE;
                        }
                    })
            );

        }  else {
            //Toast.makeText(EditEntryActivity.this, "Seems no data is available for this record", Toast.LENGTH_LONG).show();
            //return;
            refreshData();
        }
    }

    void reloadNutritionDic(Double weight, Double servings) {
        if (weight == null) {
            weight = CaloriesManager.SERVING_WEIGHT;
        } else {

            if (mCalorieData != null && mCalorieData.getNutrition() != null) {
                HashMap<String, Double> map = new HashMap<>(mCalorieData.getNutritionAsMap());
                map.put(CaloriesManager.PROPERTY_SERVING_WEIGHT, weight * 1000 * servings);
                mCalorieData.setNutritionFromMap(map);

            }
        }
        if (mCalorieData != null && mCalorieData.getNutrition() != null) {
            mMealDictionary = new HashMap<>(mCalorieData.getNutritionAsMap());

            for (String mkey : mMealDictionary.keySet()) {
                CaloriesInfoData dFile = getCaloriesNutritionDataForKey(mkey);
                if (dFile != null) {
                    Double returnValue = CaloriesManager.getCaloriesInfoData(dFile, mMealDictionary, mkey, servings, weight);
                    mMealDictionary.put(mkey, returnValue);
                }
            }

            mMealDictionary.put(CaloriesManager.PROPERTY_SERVING_WEIGHT, weight * 1000 * servings);

            CaloriesManager.formatCaloriesLabel(mMealDictionary, mLblTotalCalories, mLblTotalCarbs, mLblTotalFats, mLblTotalProteins);
        }
    }

    CaloriesInfoData getCaloriesNutritionDataForKey(String key) {
        Map<String, CaloriesInfoData> map = mNutritionData.getInfo();

        for (String data : map.keySet()) {
            if (data.equalsIgnoreCase(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCalorieData != null) {
            if (mCalorieData.getNumberOfServings() > 0 && mCalorieData.getServingSize() != null) {
                mCurrentNumberOfServing = mCalorieData.getNumberOfServings();
                mCurrentServingUnit = mCalorieData.getServingSize().getUnit();
            }

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String id = extras.getString(CaloriesManager.PROPERTY_ID);
                mMealType = extras.getString(CaloriesManager.PROPERTY_TYPE);
                loadFoodInfoServer(id, mMealType);
            }
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String id = extras.getString(CaloriesManager.PROPERTY_ID);
                mMealType = extras.getString(CaloriesManager.PROPERTY_TYPE);
                loadFoodInfoServer(id, mMealType);
            }
        }
    }

    String servingSize() {
        return mCurrentServingUnit;
    }

    String numberOfServings() {
        NumberFormat fs = NumberFormat.getInstance();
        fs.setMaximumFractionDigits(3);
        fs.setGroupingUsed(false);
        if (mCurrentNumberOfServing != null) {
            String str = fs.format(mCurrentNumberOfServing);
            return str;
        } else {
            return "";
        }
    }

    private void setServingUnit(TextView numberOfServings, String caption, String description) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        int start = sb.length();
        sb.append(caption);
        sb.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, getResources().getDimensionPixelSize(R.dimen.serving_unit_text), null, null), start, start + caption.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        String sedText = "\n" + description;
        start = sb.length();
        if (description.length() > 0) {
            sb.append(sedText);
            sb.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, getResources().getDimensionPixelSize(R.dimen.serving_unit_textsize), null, null), start,
                    start + sedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            numberOfServings.setText(sb);
        } else {
            numberOfServings.setText(sb);
        }
    }

    public class CaloriesEditEntryAdapter extends BaseExpandableListAdapter implements TextWatcher {

        public Object getChild(int groupPosition, int childPosition) {
            return mNutritionData.getNutrition().get(childPosition);
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            int position = 1;
            if (groupPosition == 0) {
                position = 1;
            } else if (groupPosition == 1) {
                position = mNutritionData.getNutrition().size();
            }
            return position;
        }

        public Object getGroup(int groupPosition) {
            return null;
        }

        public int getGroupCount() {
            return 2;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(EditEntryActivity.this);
                row = inflater.inflate(R.layout.cell_edit_group_recent_list, null);
            }

            TextView title = row.findViewById(R.id.cell_recent_lunch_type);
            TextView textVerified = row.findViewById(R.id.cell_verified_text);
            RelativeLayout verifiedLayout = row.findViewById(R.id.verifiedLayout);
            TextView iconImp = row.findViewById(R.id.icon_important);
            downarrow = row.findViewById(R.id.downarrow);
            RelativeLayout headerLayout = row.findViewById(R.id.menulist);
            RelativeLayout innerView = row.findViewById(R.id.view);

            CenteredCustomFontView editRecipe = row.findViewById(R.id.edit_recipe);
            editRecipe.setText(ArgusIconMap.getInstance().get(ArgusIconMap.EDIT));
            if (mCalorieData != null) {
                if (groupPosition == 0) {
                    ViewGroup.LayoutParams params = headerLayout.getLayoutParams();
                    params.height = getResources().getDimensionPixelOffset(R.dimen.layout_group_height);
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    headerLayout.setLayoutParams(params);
                    downarrow.setVisibility(View.GONE);
                    ViewGroup.LayoutParams params1 = innerView.getLayoutParams();
                    params1.height = getResources().getDimensionPixelOffset(R.dimen.layout_group_height);
                    params1.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    innerView.setLayoutParams(params1);
                    headerLayout.setBackgroundColor(ContextCompat.getColor(EditEntryActivity.this, R.color.background_color));
                    title.setText(mCalorieData.getName());
                    title.setTextSize(20.0f);
                    title.setTextColor(ContextCompat.getColor(EditEntryActivity.this, R.color.text_color));

                        if (mCalorieData.getValidated()) {
                            verifiedLayout.setVisibility(View.VISIBLE);
                            textVerified.setVisibility(View.VISIBLE);
                            iconImp.setVisibility(View.VISIBLE);
                        } else {
                            verifiedLayout.setVisibility(View.GONE);
                            textVerified.setVisibility(View.GONE);
                            iconImp.setVisibility(View.GONE);
                        }


                    if (mCalorieData.getFoods() != null) {
                        if (mCalorieData.getFoods().size() > 0) {
                            editRecipe.setVisibility(View.VISIBLE);
                        } else {
                            editRecipe.setVisibility(View.INVISIBLE);
                        }
                    }
                } else if (groupPosition == 1) {
                    ViewGroup.LayoutParams params = headerLayout.getLayoutParams();
                    params.height = getResources().getDimensionPixelOffset(R.dimen.layout_height);
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    headerLayout.setLayoutParams(params);

                    ViewGroup.LayoutParams params1 = innerView.getLayoutParams();
                    params1.height = getResources().getDimensionPixelOffset(R.dimen.layout_height);
                    params1.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    innerView.setLayoutParams(params1);
                    downarrow.setVisibility(View.VISIBLE);
                    if (!groupExpanded) {
                        downarrow.setBackgroundResource(R.drawable.downarrow);
                    } else {
                        downarrow.setBackgroundResource(R.drawable.uparrow);
                    }
                    headerLayout.setBackgroundColor(ContextCompat.getColor(EditEntryActivity.this, R.color.white));
                    title.setText(getString(R.string.nutrition_facts));
                    title.setTextSize(15.0f);
                    title.setTextColor(ContextCompat.getColor(EditEntryActivity.this, R.color.text_color));
                    textVerified.setVisibility(View.GONE);
                    verifiedLayout.setVisibility(View.GONE);
                    editRecipe.setVisibility(View.GONE);
                    iconImp.setVisibility(View.GONE);
                }


            }
            return row;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View row = convertView;
            EditEntryItemsWrapper wrapper = null;
            EditEntryRecyclerViewWrapper wrapper1 = null;

            switch (groupPosition) {
                case 0:
                    LayoutInflater inflater = LayoutInflater.from(EditEntryActivity.this);
                    row = inflater.inflate(R.layout.cell_calories_servings, null);
                    mTextServingcount = row.findViewById(R.id.cell_serving_count);
                    mNumberServingCount = row.findViewById(R.id.cell_numberserving_count);
                    wrapper1 = new EditEntryRecyclerViewWrapper(row);
                    break;
                case 1:
                    LayoutInflater inflater1 = LayoutInflater.from(EditEntryActivity.this);
                    row = inflater1.inflate(R.layout.cell_calories_nutritions, null);
                    wrapper = new EditEntryItemsWrapper(row);
                    break;
            }


            if (groupPosition == 0) {
                mTextServingcount.setText(servingSize());
                if (servingValue.length() > 0) {
                    mNumberServingCount.setText(servingValue);
                } else {
                    mNumberServingCount.setText(numberOfServings());
                }
                List<SegmentResponse.FoodItem.ServingSize> sizeDatas = mCalorieData.getServingSizes();
                wrapper1.populateFrom(sizeDatas);
                mNumberServingCount.addTextChangedListener(this);
            }
            if (groupPosition == 1) {
                wrapper.populateFrom(mNutritionData.getNutrition().get(childPosition), groupPosition);
            }
            return (row);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mNumberServingCount != null) {
                if (!isfractionClick) {
                    mNumberServingCount.removeTextChangedListener(this);
                    if (!s.toString().equalsIgnoreCase(".")) {
                        servingValue = s.toString();
                        if (servingValue.length() > 0) {
                            mCurrentNumberOfServing = NumberFormatUtils.parseStringToDouble(servingValue);
                            //mNumberServingCount.setText(s);
                        }
                    }
                    mNumberServingCount.addTextChangedListener(this);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    class EditEntryRecyclerViewWrapper {
        private View row = null;
        private HorizontalAdapter horizontalAdapter;
        private Button fractionbtn, decimalbtn;
        private RecyclerView recyclerView;

        public EditEntryRecyclerViewWrapper(View r) {
            this.row = r;
            getRecyclerView();
            getFractionView();
            getDecimalView();
        }

        public void populateFrom(List<SegmentResponse.FoodItem.ServingSize> servingSizeDatas) {
            if (horizontalAdapter == null) {
                horizontalAdapter = new HorizontalAdapter(servingSizeDatas, getRecyclerView());
                recyclerView.setLayoutManager(new LinearLayoutManager(EditEntryActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }

            recyclerView.setAdapter(horizontalAdapter);
            if (mNumberServingCount != null) {
                mNumberServingCount.setOnEditorActionListener((v, actionId, event) ->
                {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        mNumberServingCount.clearFocus();
                        reloadNutritionDic(mCurrentServingWeight, mCurrentNumberOfServing);
                        mAdapter.notifyDataSetChanged();
                        return false;
                    }
                    return false;
                });
            }
        }

        RecyclerView getRecyclerView() {
            if (recyclerView == null) {
                recyclerView = row.findViewById(R.id.horizontal_recycler_view);
            }
            return (recyclerView);
        }

        Button getFractionView() {
            if (fractionbtn == null) {
                fractionbtn = row.findViewById(R.id.fractionBtn);
            }
            return fractionbtn;
        }

        Button getDecimalView() {
            if (decimalbtn == null) {
                decimalbtn = row.findViewById(R.id.decimalBtn);
            }
            return decimalbtn;
        }
    }

    class EditEntryItemsWrapper {
        private TextView mTextView = null;
        private TextView mEditText = null;
        private View row = null;

        public EditEntryItemsWrapper(View r) {
            this.row = r;
            getCellTextLabel();
            getCellTextValue();
        }

        public void populateFrom(CaloriesNutritionData nutritionData, int groupPosition) {
            if (mCalorieData == null || groupPosition != 1 || nutritionData == null) {
                return;
            }

            if (nutritionData.getBold() > -1) {
                boolean isBold = nutritionData.getBold() == 1;
                float textSize = isBold ? 15.0f : 12.0f;
                int color = ContextCompat.getColor(EditEntryActivity.this, isBold ? R.color.edit_calories : R.color.edit_calories_light_color);
                if (getCellTextLabel() != null && getCellTextValue() != null) {
                    if (isBold) {
                        getCellTextLabel().setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto_medium.ttf"));
                    }
                    getCellTextLabel().setTextSize(textSize);
                    getCellTextValue().setTextSize(textSize);
                    getCellTextLabel().setTextColor(color);
                    getCellTextValue().setTextColor(color);
                }
            }

            if (nutritionData.getTabbed() > -1) {
                if (getCellTextLabel() != null) {
                    getCellTextLabel().setPadding(nutritionData.getTabbed() == 1 ? 40 : 0, 0, 0, 0);
                }
            }

            if (mCalorieData.getNutrition() != null) {
                if (!mCalorieData.getNutritionAsMap().containsKey(nutritionData.getKey())) {
                    if (getCellTextValue() != null) {
                        if (getCellTextLabel() != null) {
                            getCellTextLabel().setText(nutritionData.getName());
                        }
                        getCellTextValue().setText("-");
                    }
                }

                if (getCellTextLabel() != null) {
                    getCellTextLabel().setText(nutritionData.getName());

                }
                if (mCalorieData == null) {
                    return;
                }
                if (mCalorieData.getNutritionAsMap().containsKey(nutritionData.getKey())) {
                    NumberFormat nf = NumberFormat.getInstance();
                    if (nutritionData.getKey().equalsIgnoreCase(CaloriesManager.PROPERTY_CALORIES)) {
                        nf.setMaximumFractionDigits(0);
                    } else {
                        nf.setMaximumFractionDigits(1);
                    }

                    Double value = Double.valueOf(mMealDictionary.get(nutritionData.getKey()));
                    if (getCellTextValue() != null) {
                        if (nutritionData.getKey().equalsIgnoreCase(CaloriesManager.PROPERTY_CALORIES)) {
                            getCellTextValue().setText(String.format("%s %s", nf.format(value), Character.toString(nutritionData.getUnit().charAt(0)).toUpperCase() + nutritionData.getUnit().substring(1)));
                        } else {
                            getCellTextValue().setText(String.format("%s %s", nf.format(value), nutritionData.getUnit()));
                        }
                    }
                }
            } else {
                if (getCellTextLabel() != null) {
                    getCellTextValue().setText("-");
                }
                if (getCellTextLabel() != null) {
                    getCellTextLabel().setText(nutritionData.getName());
                }
            }
        }

        TextView getCellTextLabel() {
            if (mTextView == null) {
                mTextView = row.findViewById(R.id.cell_text_label);
            }
            return (mTextView);
        }

        TextView getCellTextValue() {
            if (mEditText == null) {
                mEditText = row.findViewById(R.id.cell_text_value);
            }
            return (mEditText);
        }
    }

    protected class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
        private List<SegmentResponse.FoodItem.ServingSize> mSizeDatas;
        private RecyclerView recyclerView;

        public HorizontalAdapter(List<SegmentResponse.FoodItem.ServingSize> mSizeDatas, RecyclerView recyclerView) {
            this.mSizeDatas = mSizeDatas;
            this.recyclerView = recyclerView;
        }

        @Override
        public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_view, parent, false);
            mViewHolder = new HorizontalAdapter.ViewHolder(itemView);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position) {
            if(position < mSizeDatas.size()) {
                SegmentResponse.FoodItem.ServingSize sizeData = mSizeDatas.get(position);
                splitServingSizeUnitName(holder, sizeData);
                highlightSelectedUnit(holder, position, sizeData);

                if (selected_position > 0) {
                    recyclerView.smoothScrollToPosition(selected_position);
                }

                onServingUnitClick(holder, position, sizeData);
            }
        }

        private void onServingUnitClick(ViewHolder holder, int position, SegmentResponse.FoodItem.ServingSize sizeData) {
            holder.servingUnit.setOnClickListener(v ->
            {
                holder.servingUnit.setTextColor(ContextCompat.getColor(EditEntryActivity.this, R.color.selected_text_color));
                notifyItemChanged(selected_position);
                selected_position = position;
                mCurrentServingUnit = sizeData.getUnit();
                if (sizeData.getServingWeight() >0 ) {
                    mCurrentServingWeight = Double.valueOf(sizeData.getServingWeight());
                }
                mCurrentServingUnit = sizeData.getUnit();
                reloadNutritionDic(mCurrentServingWeight, mCurrentNumberOfServing);
                notifyItemChanged(selected_position);
                mAdapter.notifyDataSetChanged();
            });
        }

        private void highlightSelectedUnit(ViewHolder holder, int position, SegmentResponse.FoodItem.ServingSize sizeData) {
            if (mCurrentServingUnit.equalsIgnoreCase(sizeData.getUnit())) {
                selected_position = position;

                holder.mainView.setBackgroundResource(R.drawable.orange_rect_border);
                holder.servingUnit.setTextColor(ContextCompat.getColor(EditEntryActivity.this, R.color.selected_text_color));
                if (mSizeDatas.get(position).getServingWeight() > 0) {
                    mCurrentServingWeight = Double.valueOf(sizeData.getServingWeight());
                }
            } else {
                holder.mainView.setBackgroundResource(R.drawable.corner_radius_rect_border);
                holder.servingUnit.setTextColor(ContextCompat.getColor(EditEntryActivity.this, R.color.text_color));
            }
        }

        private void splitServingSizeUnitName(ViewHolder holder, SegmentResponse.FoodItem.ServingSize sizeData) {
            if (sizeData.getUnit().contains("(")) {
                String[] spiltedValue = sizeData.getUnit().split("\\(");
                if (spiltedValue.length > 1) {
                    setServingUnit(holder.servingUnit, spiltedValue[0], "(" + spiltedValue[1]);
                }
            } else if (sizeData.getUnit().contains(",")) {
                String[] spiltedValue = sizeData.getUnit().split(",");
                if (spiltedValue.length > 1) {
                    setServingUnit(holder.servingUnit, spiltedValue[0], spiltedValue[1]);
                }
            } else {
                setServingUnit(holder.servingUnit, sizeData.getUnit(), "");
            }
        }

        @Override
        public int getItemCount() {
            if (mSizeDatas != null) {
                return mSizeDatas.size();
            } else {
                return 0;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView servingUnit;
            private RelativeLayout mainView;

            public ViewHolder(View view) {
                super(view);
                servingUnit = view.findViewById(R.id.servingUnit);
                mainView = view.findViewById(R.id.view);
            }
        }
    }

}
