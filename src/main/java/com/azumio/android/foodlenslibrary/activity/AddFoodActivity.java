package com.azumio.android.foodlenslibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.SizeF;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.adapter.BasicFragmentsViewPagerAdapter;
import com.azumio.android.foodlenslibrary.adapter.BottomSelectedListAdapter;
import com.azumio.android.foodlenslibrary.api.APIClient;
import com.azumio.android.foodlenslibrary.api.Continuation;
import com.azumio.android.foodlenslibrary.api.FoodLensService;
import com.azumio.android.foodlenslibrary.common.DataWrapper;
import com.azumio.android.foodlenslibrary.fragment.OnSearchListener;
import com.azumio.android.foodlenslibrary.fragment.SearchQuickFragment;
import com.azumio.android.foodlenslibrary.fragment.SearchRecentFragment;
import com.azumio.android.foodlenslibrary.fragment.SearchSuggestionFragment;
import com.azumio.android.foodlenslibrary.fragment.SelectFoodFragment;
import com.azumio.android.foodlenslibrary.model.CaloriesSearchLog;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;
import com.azumio.android.foodlenslibrary.utils.CaloriesSearchLogger;
import com.azumio.android.foodlenslibrary.utils.ColorUtils;
import com.azumio.android.foodlenslibrary.utils.DialogUtils;
import com.azumio.android.foodlenslibrary.utils.KeyboardUtils;
import com.azumio.android.foodlenslibrary.utils.PremiumStatus;
import com.azumio.android.foodlenslibrary.utils.TextUtils;
import com.azumio.android.foodlenslibrary.utils.datetime.MealTimeHelper;
import com.azumio.android.foodlenslibrary.views.FillingView;
import com.azumio.android.foodlenslibrary.views.ViewPagerTabView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Response;

import static com.azumio.android.foodlenslibrary.utils.ContextUtils.isGoneOrFinishing;
import static com.azumio.android.foodlenslibrary.utils.ContextUtils.isNotFinishing;
import static com.azumio.android.foodlenslibrary.utils.PremiumStatus.isPremium;


public class AddFoodActivity extends BaseFragmentActivity {

    public static final String INGREDIENTS_KEY = "Ingredients";
    public static final String FOOD_TYPE_EXTRA_KEY = "food_type";
    public static final String ADDITIONAL_POINT_EXTRA_KEY = "additional_point";
    public static final String IMAGE_CACHE_ID_EXTRA_KEY = "image_cache_id";
    public static final String REMOVE_NO_MATCH_EXTRA_KEY = "removeNoMatch";
    public static final int FIND_FOOD_REQUEST_CODE = 1011;
    public static final int RESULT_CODE_EDITMODE = 1012;
    public static final String ERROR_CODE = "404";
    public static final String MODE_RETURN_FOOD = "modeReturnFood";
    public static final int MANUAL_SEARCH = 1013;
    public static final String SEARCH_QUERY = "searchQuery";
    public static final String SELECTED_MEAL_TYPE = "Selected_Meal_Type";
    private static final String LOG_TAG = AddFoodActivity.class.getSimpleName();
    private static final String STATE_EXTRA_KEY = "state";
    private static final String TAG = "tag";
    public String type = "";
    public SelectFoodFragment fragment = new SelectFoodFragment();

   // @BindView(R.id.searchtext)
    public SearchView searchView;

   // @BindView(R.id.activity_with_fragment_toolbar_textview)
    protected TextView textView;

   // @BindView(R.id.main_menu_toolbar)
    protected Toolbar toolbar;

  //  @BindView(R.id.main_menu_fillingview_toolbars)
    protected FillingView fillingView;

 //   @BindView(R.id.bottomLayout)
    protected RelativeLayout bottomLayout;

  //  @BindView(R.id.activity_log)
    protected TextView addLog;

  //  @BindView(R.id.loggedCount)
    protected TextView loggedCount;

  //  @BindView(R.id.lblTotalItems)
    protected TextView lblTotalItems;

  //  @BindView(R.id.activity_with_fragment_textview)
    protected TextView mTitle;

   // @BindView(R.id.lblTotalCals)
    protected TextView lblTotalCals;

  //  @BindView(R.id.logItemsView)
    protected RelativeLayout logItemsView;

  //  @BindView(R.id.countView)
    protected RelativeLayout countView;

   // @BindView(R.id.transparentLayout)
    protected RelativeLayout transparentView;

  //  @BindView(R.id.close_btn)
    protected ImageView closeBtn;

   // @BindView(R.id.listview)
    protected ExpandableListView listView;

  //  @BindView(R.id.bottomView)
    protected LinearLayout bottomView;

 //   @BindView(R.id.view_pager)
    protected ViewPager viewPager;

 //   @BindView(R.id.view_pager_tab_view)
    protected ViewPagerTabView viewPagerTabView;

    CaloriesSearchLogger searchLogger = new CaloriesSearchLogger();
    HashMap<String, List<FoodSearchData>> selectedData;
    List<String> queuedFoodIds = new ArrayList<>();
    private boolean showRecipe = true;
    private Boolean isSaving = false;
    private boolean isClicked;
    private Pair<Integer, Integer> selectedRow;

    //when this field is true, activity don't add food to checkin,
    // just return selected food via intent
    private boolean modeReturnFood;
    private String searchQuery;

    private String imageCacheId;
    private  String additionalPoint;

    private BottomSelectedListAdapter adapter;
    private CaloriesSearchLog caloriesSearchLog;
    private BasicFragmentsViewPagerAdapter caloriesSearchFragmentPager;
    private DialogUtils dialogUtils;

    public static void startForFoodResult(Activity context) {
        startForFoodResult(context, null);
    }

    public static void startForFoodResult(Activity context, String meal) {
        Intent intent = createIntent(meal, context, true);
        context.startActivityForResult(intent, AddFoodActivity.FIND_FOOD_REQUEST_CODE);
    }

    public static void startForFoodResult(Fragment fragment) {
        startForFoodResult(fragment, null);
    }

    public static void startForFoodResult(Fragment fragment, String meal) {
        Intent intent = createIntent(meal, fragment.getContext(), true);
        fragment.startActivityForResult(intent, AddFoodActivity.FIND_FOOD_REQUEST_CODE);
    }


    public static void startForFoodResult(Fragment fragment, String meal, PointF additionalPoints, String imageCacheid) {
        Intent intent = createIntent(meal,String.format("[[%f,%f]]",additionalPoints.x,additionalPoints.y),imageCacheid, fragment.getContext(), true);
        fragment.startActivityForResult(intent, AddFoodActivity.FIND_FOOD_REQUEST_CODE);
    }

    public static void start(Fragment fragment) {
        Intent intent = createIntent(null, fragment.getContext(), false);
        fragment.startActivity(intent);
    }




    @NotNull
    private static Intent createIntent(String meal, Context context, boolean modeReturnFood) {
        String mealLabel = meal == null ? MealTimeHelper.getMealLabelByTimeOfDay() : meal;
        Intent intent = (new Intent(context, AddFoodActivity.class));
        intent.putExtra(AddFoodActivity.MODE_RETURN_FOOD, modeReturnFood);
        intent.putExtra(AddFoodActivity.FOOD_TYPE_EXTRA_KEY, mealLabel);
        return intent;
    }


    @NotNull
    private static Intent createIntent(String meal, String additionalPoint, String imageCacheId, Context context, boolean modeReturnFood) {
        String mealLabel = meal == null ? MealTimeHelper.getMealLabelByTimeOfDay() : meal;
        Intent intent = (new Intent(context, AddFoodActivity.class));
        intent.putExtra(AddFoodActivity.MODE_RETURN_FOOD, modeReturnFood);
        intent.putExtra(AddFoodActivity.FOOD_TYPE_EXTRA_KEY, mealLabel);
        intent.putExtra(AddFoodActivity.IMAGE_CACHE_ID_EXTRA_KEY, imageCacheId);
        intent.putExtra(AddFoodActivity.ADDITIONAL_POINT_EXTRA_KEY, additionalPoint);

        return intent;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_addfood_calories);
       // ButterKnife.bind(this);

          searchView = findViewById(R.id.searchtext);
       textView = findViewById(R.id.activity_with_fragment_toolbar_textview);
      toolbar = findViewById(R.id.main_menu_toolbar);
       fillingView = findViewById(R.id.main_menu_fillingview_toolbars);
         bottomLayout = findViewById(R.id.bottomLayout);
         addLog = findViewById(R.id.activity_log);
         loggedCount = findViewById(R.id.loggedCount);
         lblTotalItems = findViewById(R.id.lblTotalItems);
        mTitle = findViewById(R.id.activity_with_fragment_textview);
        lblTotalCals = findViewById(R.id.lblTotalCals);
        logItemsView = findViewById(R.id.logItemsView);
        countView = findViewById(R.id.countView);
         transparentView = findViewById(R.id.transparentLayout);
         closeBtn = findViewById(R.id.close_btn);
        listView = findViewById(R.id.listview);
        bottomView = findViewById(R.id.bottomView);
        viewPager = findViewById(R.id.view_pager);
       viewPagerTabView  = findViewById(R.id.view_pager_tab_view);


        dialogUtils = new DialogUtils(this);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(v -> finish());
        textView.setText(getString(R.string.add_breakfast));
        closeBtn.setBackgroundResource(R.drawable.abc_ic_clear_material);
        RelativeLayout foodLayout = (RelativeLayout) findViewById(R.id.foodLayout);
        ImageView barcodeScanner = (ImageView) findViewById(R.id.barcodeScanner);


        EditText searchEdit = ((EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text));
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchView.setBackgroundColor(Color.TRANSPARENT);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search for a food");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String sText) {
                onSearchSubmit(sText);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (isGoneOrFinishing(AddFoodActivity.this) || TextUtils.isEmpty(s)) {
                    if (checkActiveFragment() != null) {
                        checkActiveFragment().clearSearch(s);
                    }
                    //		return false;
                }
                if (checkActiveFragment() != null) {
                    checkActiveFragment().onSearchTextChange(s);
                }
                return true;
            }
        });



        int search_close_btnId = androidx.appcompat.R.id.search_close_btn;
        ImageView search_close_btn = (ImageView) searchView.findViewById(search_close_btnId);

        search_close_btn.setOnClickListener(view ->
        {
            searchView.setQueryHint(getString(R.string.search_food));
            searchView.clearFocus();
            searchView.setQuery("", false);
        });

        if (searchView.toString().length() == 0) {
            KeyboardUtils.hideSoftKeyboard(searchView);
        }


            ColorUtils.setToolbarTextAndIconColors(toolbar, ContextCompat.getColor(this, R.color.white));
            ColorUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.calories_statusbar_color), ContextCompat.getColor(this, R.color.calories_color));

        isClicked = false;
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getBoolean(MODE_RETURN_FOOD)) {
                modeReturnFood = true;
            }

            searchQuery = bundle.getString(SEARCH_QUERY);
            bundle.remove(SEARCH_QUERY);

            if (bundle.getBoolean(INGREDIENTS_KEY, false)) {
                showRecipe = false;
                foodLayout.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(getString(R.string.add_ingredients_label));
                addLog.setText(getString(R.string.add));
                setType(INGREDIENTS_KEY);
            } else if (bundle.containsKey(FOOD_TYPE_EXTRA_KEY)) {
                setType(bundle.getString(FOOD_TYPE_EXTRA_KEY).toLowerCase());
                mTitle.setText(getString(R.string.add_label) + " " + getType().toLowerCase());
            }

            if(bundle.containsKey(ADDITIONAL_POINT_EXTRA_KEY))
            {
                this.additionalPoint =  bundle.getString(ADDITIONAL_POINT_EXTRA_KEY);
            }
            if(bundle.containsKey(IMAGE_CACHE_ID_EXTRA_KEY))
            {
                this.imageCacheId =  bundle.getString(IMAGE_CACHE_ID_EXTRA_KEY);
            }

            bundle.clear();

        } else {
            setType(getString(R.string.breakfast));
            addLog.setText(getString(R.string.log));
        }

        openSearchView();
        initViewPager();
        bottomLayout.setOnClickListener(view ->
        {
            if (fragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            if (isClicked) {
                closeView(transparentView);
            } else {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.setMargins(0, getResources().getDimensionPixelOffset(R.dimen.calories_margin), 0, 0);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                listView.setVisibility(View.VISIBLE);
                bottomView.setLayoutParams(params);
                listView.setLayoutParams(params1);
                addLog.setVisibility(View.GONE);
                adapter = new BottomSelectedListAdapter(selectedData, AddFoodActivity.this, getType().equalsIgnoreCase(INGREDIENTS_KEY));
                listView.setAdapter(adapter);
                //	mMainLayout.setBackgroundColor(ContextCompat.getColor(AddFoodActivity.this, R.color.split_black_alpha));
                adapter.setonCheckChangelisterner((isChecked, foodItem) ->
                {
                    if (!isChecked) {
                        removeFromSelectedList(foodItem.getId());
                        adapter.notifyDataSetChanged();
                        if (checkActiveFragment() != null) {
                            checkActiveFragment().onRemovedItem(foodItem);
                        }
                    }
                });

                if (selectedData != null) {
                    for (int i = 0; i < selectedData.size(); i++) {
                        listView.expandGroup(i);
                    }
                }

                listView.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
                listView.setOnChildClickListener((expandableListView, view1, groupPosition, childPosition, l) ->
                {
                    if (adapter != null) {
                        handleChildClick(groupPosition, childPosition);
                    }
                    return true;
                });


                isClicked = true;
                if (checkActiveFragment() != null) {
                    checkActiveFragment().onViewVisibility(false);
                }
                transparentView.setVisibility(View.VISIBLE);
                closeBtn.setVisibility(View.VISIBLE);
            }
        });

        transparentView.setOnClickListener(view -> closeView(transparentView));
        addLog.setOnClickListener(view -> save());
        logItemsView.setOnClickListener(view -> save());
        fragment.setFoodType(getType());

        foodLayout.setOnClickListener(view ->
        {
            if (fragment == null) {
                fragment = new SelectFoodFragment();
                fragment.setFoodType(getType());
            }

            ArrayList<String> arrayValues = new ArrayList<>(Arrays.asList(CaloriesManager.MEAL_ORDER));
            fragment.setSelectedPosition(arrayValues.indexOf(fragment.getFoodType()));
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_with_fragment_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commitAllowingStateLoss();
        });

        fragment.setAdapterListener((position, value) ->
        {
            mTitle.setText(getString(R.string.add_label) + " " + value.toLowerCase());
            fragment.setFoodType(value.toLowerCase());
            setType(value.toLowerCase());
            if (checkActiveFragment() != null) {
                checkActiveFragment().onMealTypeChanged(getType());
            }
        });

        setSearchViewEditTextBackgroundColor(this, searchView, R.color.white);
    }

    private void initViewPager() {
        caloriesSearchFragmentPager = new BasicFragmentsViewPagerAdapter(LOG_TAG, getSupportFragmentManager(), getFragmentsDefinitions());
        viewPager.setAdapter(caloriesSearchFragmentPager);
        viewPagerTabView.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fillingView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
    }

    private BasicFragmentsViewPagerAdapter.FragmentDefinition newRecentFragmentDefinition() {
        Bundle arguments = new Bundle();
        return new BasicFragmentsViewPagerAdapter.FragmentDefinition(getString(R.string.recent), SearchRecentFragment.class, arguments);
    }

    private BasicFragmentsViewPagerAdapter.FragmentDefinition newQuickAddFragmentDefinition() {
        Bundle arguments = new Bundle();
        return new BasicFragmentsViewPagerAdapter.FragmentDefinition(getString(R.string.quick_add), SearchQuickFragment.class, arguments);
    }

    private BasicFragmentsViewPagerAdapter.FragmentDefinition newSuggestionFragmentDefinition() {
        Bundle arguments = new Bundle();
        arguments.putString(ADDITIONAL_POINT_EXTRA_KEY,this.additionalPoint);
        arguments.putString(IMAGE_CACHE_ID_EXTRA_KEY,this.imageCacheId);
        return new BasicFragmentsViewPagerAdapter.FragmentDefinition(getString(R.string.suggestions), SearchSuggestionFragment.class, arguments);
    }


    private ArrayList<BasicFragmentsViewPagerAdapter.FragmentDefinition> getFragmentsDefinitions() {
        ArrayList<BasicFragmentsViewPagerAdapter.FragmentDefinition> fragments = new ArrayList<>();
        if(this.imageCacheId != null && this.additionalPoint != null)
        {
            fragments.add(newSuggestionFragmentDefinition());
        }

        fragments.add(newRecentFragmentDefinition());
        fragments.add(newQuickAddFragmentDefinition());

        return fragments;
    }

    private void setSearchViewEditTextBackgroundColor(Context context, SearchView searchView, int backgroundColor) {
        int searchPlateId = context.getResources().getIdentifier("android:id/search_plate", null, null);
        ViewGroup viewGroup = (ViewGroup) searchView.findViewById(searchPlateId);
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(ContextCompat.getColor(context, backgroundColor));
        }
    }

    private void onSearchSubmit(String sText) {
        if (sText.length() > 0) {
            if (isNotFinishing(AddFoodActivity.this)) {
                if (checkActiveFragment() != null) {
                    checkActiveFragment().onSearchTextDone(sText);
                  //  CleverTapEventsLogger cleverTapEventsHelper = new CleverTapEventsLogger();
                  //  cleverTapEventsHelper.logEvent(CleverTapEventsLogger.CALORIES_FOOD_SEARCH);
                }
                KeyboardUtils.hideSoftKeyboard(searchView);
            }
        }
    }

    private void openSearchView() {
        if (searchView != null) {
            searchView.setIconified(false);
        }
    }

    private void closeView(RelativeLayout transparentView) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(0, 0, 0, 0);
        bottomView.setLayoutParams(params);
        listView.setVisibility(View.GONE);
        addLog.setVisibility(View.VISIBLE);
        isClicked = false;
        //	mMainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        if (checkActiveFragment() != null) {
            checkActiveFragment().onViewVisibility(true);
        }
        transparentView.setVisibility(View.GONE);
        closeBtn.setVisibility(View.GONE);
    }

    private void handleChildClick(int groupPosition, int childPosition) {
        selectedRow = new Pair<>(groupPosition, childPosition);
        FoodSearchData foodItem = (FoodSearchData) adapter.getChild(groupPosition, childPosition);

        if ((foodItem.getType() == null ? CaloriesManager.LOG_TYPE_FOOD : foodItem.getType()).equalsIgnoreCase(CaloriesManager.LOG_TYPE_QUICK)) {
            Intent intent = new Intent(AddFoodActivity.this, QuickAddActivity.class);

            try {
                intent.putExtra(CaloriesManager.PROPERTY_DATA, foodItem.jsonString());
            } catch (Exception e) {
                Log.e(LOG_TAG, "JsonProcessingException while listview click", e);
            }
            startActivityForResult(intent, RESULT_CODE_EDITMODE);
        } else {
            Intent intent = new Intent(AddFoodActivity.this, EditEntryActivity.class);
            intent.putExtra(CaloriesManager.PROPERTY_ID, (foodItem.getId() == null ? foodItem.getParentId() : foodItem.getId()));
            intent.putExtra(CaloriesManager.PROPERTY_TYPE, foodItem.getType() == null ? CaloriesManager.LOG_TYPE_FOOD : foodItem.getType());
            intent.putExtra(STATE_EXTRA_KEY, CaloriesManager.SAVE);

            try {
                intent.putExtra(CaloriesManager.PROPERTY_DATA, foodItem.jsonString());
            } catch (Exception e) {
                Log.e(LOG_TAG, "JsonProcessingException while listview click ", e);
            }
            startActivityForResult(intent, RESULT_CODE_EDITMODE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        if (searchQuery != null) {
            Observable.just(searchQuery).delay(100, TimeUnit.MILLISECONDS).filter(s -> !TextUtils.isEmpty(s)).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> searchView.setQuery(s, true));
        }
         */
    }

    @Override
    protected void onPause() {

        super.onPause();
    }



    @Nullable
    private OnSearchListener checkActiveFragment() {
        Fragment fragment = caloriesSearchFragmentPager.getItem(viewPager.getCurrentItem());

        if (fragment instanceof OnSearchListener) {
            return (OnSearchListener) fragment;
        }
        return null;
    }

    void foodQueueProcessed() {
        if (isSaving != null && isSaving) {
            save();
        }
    }

    void save() {
        if (selectedData == null) {
            return;
        }

        if (selectedData.isEmpty()) {
            dialogUtils.showAlertDialog(getString(R.string.select_fooditem), this);
            return;
        }

        addLog.setEnabled(false);
        isSaving = true;
        if (this.queuedFoodIds.size() > 0) {
            return;
        }

        if (caloriesSearchLog != null) {
            searchLogger.logSearchTerm(caloriesSearchLog);
        }
        searchLogger.logSearchToServer();

        if (modeReturnFood || getType().equalsIgnoreCase(INGREDIENTS_KEY)) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            if (selectedData != null) {
                if (selectedData.get(getType()) != null) {

                    List<FoodSearchData> data = selectedData.get(getType());

                    ArrayList<String> selectedJson = new ArrayList<>();
                    for (FoodSearchData d : data)
                    {
                        selectedJson.add(d.jsonString());
                    }


                    intent.putExtra(CaloriesManager.PROPERTY_DATA, new DataWrapper(selectedJson));
                }
                intent.putExtras(bundle);
                if (modeReturnFood) {
                    intent.putExtra(SELECTED_MEAL_TYPE, fragment.getFoodType());
                }

                if (!getType().equalsIgnoreCase(INGREDIENTS_KEY)) {
                   // CleverTapEventsLogger cleverTapEventsHelper = new CleverTapEventsLogger();
                    //cleverTapEventsHelper.logCalorieFoodLogEvent(selectedData);
                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            isSaving = false;
        }
    }

    public void addToSelectedList(FoodSearchData data) {
        if (selectedData == null) {
            selectedData = new HashMap<>();
        }

        List<FoodSearchData> mFoodSearchData = selectedData.get(getType());
        if (mFoodSearchData == null) {
            mFoodSearchData = new ArrayList<>();
        }

        Iterator<FoodSearchData> iterator = mFoodSearchData.iterator();
        while (iterator.hasNext()) {
            FoodSearchData value = iterator.next();
            if (data.getId() != null) {
                if (value.getId().equalsIgnoreCase(data.getId())) {
                    iterator.remove();
                    break;
                }
            }
        }

        mFoodSearchData.add(data);
        selectedData.put(getType(), mFoodSearchData);
        if (data.getNutrition() == null) {
            loadFoodInfoServer(data.getId(), data);
        }
        updateFooterStats();
    }

    void loadFoodInfoServer(String id, final FoodSearchData data) {
        this.queuedFoodIds.add(id);

        FoodLensService apiService = APIClient.INSTANCE.createService(FoodLensService.class);
        BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO(), CoroutineStart.DEFAULT, (coroutineScope, continuation) ->
                apiService.getFoodDetail(id, new Continuation<Response<FoodSearchData>>() {
                    @Override
                    public void resume(Response<FoodSearchData> value) {
                        if (isNotFinishing(AddFoodActivity.this)) {
                            AddFoodActivity.this.runOnUiThread(() -> {
                                queuedFoodIds.remove(data.getId());
                                updateWithFoodInfo(value.body());
                                updateFooterStats();

                                if (queuedFoodIds.size() < 1) {
                                    foodQueueProcessed();
                                }
                            });

                        }
                    }

                    @Override
                    public void resumeWithException(@NotNull Throwable exception) {
                        if (isNotFinishing(AddFoodActivity.this)) {
                            AddFoodActivity.this.runOnUiThread(() -> {
                                queuedFoodIds.remove(data.getId());
                                if (data.getCalories() > 0) {
                                    FoodSearchData foodSearchData = data;
                                    if (foodSearchData.getNutrition() == null) {
                                        foodSearchData.setNutritionFromMap(new HashMap<>());
                                    }
                                    foodSearchData.getNutrition().setCalories(foodSearchData.getCalories());
                                    updateWithFoodInfo(foodSearchData);
                                    updateFooterStats();
                                }

                                if (queuedFoodIds.size() < 1) {
                                    foodQueueProcessed();
                                }
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

                //String path = String.format(BuildConfig.API_CALORIES_FOOD_DETAIL + "%s", id);
    }

    public void updateWithFoodInfo(FoodSearchData data) {
        for (String key : selectedData.keySet()) {
            List<FoodSearchData> sData = selectedData.get(key);
            for (int i = 0; i < sData.size(); i++) {
                FoodSearchData searchData = sData.get(i);
                if (searchData.getId().equalsIgnoreCase(data.getId())) {
                    FoodSearchData objectToReplace = data;
                    if (objectToReplace.getServingSize() == null) {
                        if (objectToReplace.getServingSizes() != null) {
                            List<SegmentResponse.FoodItem.ServingSize> servingSizes = objectToReplace.getServingSizes();

                            if (servingSizes.size() > 0) {
                                SegmentResponse.FoodItem.ServingSize sizeData = servingSizes.get(0);
                                if (sizeData.getServingWeight() <= 0) {
                                    sizeData.setServingWeight(Double.valueOf(CaloriesManager.CALORIES_WEIGHT));
                                }

                                objectToReplace.setServingSize(sizeData);
                                objectToReplace.setNumberOfServings(CaloriesManager.NUMBER_OF_SERVINGS);
                            }
                        }
                    }
                    sData.set(i, objectToReplace);
                }
            }
        }
    }

    public List<FoodSearchData> getSelectedList() {
        if (selectedData != null) {
            return selectedData.get(this.getType());
        } else {
            return new ArrayList<>();
        }
    }

    void updateFooterStats() {
        int totalItems = 0;
        double totalCal = 0;
        for (String key : selectedData.keySet()) {
            List<FoodSearchData> data = selectedData.get(key);
            if (data.size() > 0) {
                HashMap<String, Double> nutrition = CaloriesManager.getNutritionSummation(data);
                totalItems += data.size();
                if (nutrition.get(CaloriesManager.PROPERTY_CALORIES) != null) {
                    totalCal += Double.valueOf(nutrition.get(CaloriesManager.PROPERTY_CALORIES));
                }
            }
        }

        lblTotalItems.setText(String.format("%d %s " + getString(R.string.selected), totalItems, totalItems > 1 ? CaloriesManager.ITEMS : CaloriesManager.ITEM));
        lblTotalCals.setText(String.format("%d " + getString(R.string.cal_total), (long) totalCal));
        if (totalItems > 0) {
            countView.setVisibility(View.VISIBLE);
            loggedCount.setText(totalItems + "");
            loggedCount.setTextColor(ContextCompat.getColor(this, R.color.calories_color));
        } else {
            countView.setVisibility(View.INVISIBLE);
            addLog.setText(R.string.log_value);
        }
    }

    public boolean containsFoodItem(FoodSearchData foodItem, String mealType) {
        String foodId = foodItem.getId() == null ? "" : foodItem.getId();
        if (selectedData != null) {
            List<FoodSearchData> data = selectedData.get(mealType);
            if (data != null) {
                for (FoodSearchData map : data) {
                    if (map.getId() != null) {
                        if (foodId.equalsIgnoreCase(map.getId())) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                return false;
            }
        }
        return false;
    }

    public void removeFromSelectedList(String foodId) {
        FoodSearchData mapFound = null;
        List<FoodSearchData> data = selectedData.get(getType());
        if (data != null) {
            for (FoodSearchData map : data) {
                if (foodId.equalsIgnoreCase(map.getId())) {
                    mapFound = map;
                    break;
                }
            }
            if (mapFound != null) {
                data.remove(mapFound);
                selectedData.put(getType(), data);
            }
        }
        updateFooterStats();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            FoodSearchData dataItem;
         //   ObjectMapper objectMapper = new ObjectMapper();
            try {
                if (data.getStringExtra(CaloriesManager.PROPERTY_DATA) != null) {
                    Gson gson = new Gson();
                    dataItem = gson.fromJson(data.getStringExtra(CaloriesManager.PROPERTY_DATA),FoodSearchData.class);

                    if (data.getStringExtra(CaloriesManager.PROPERTY_UNIT) != null) {
                        FoodSearchData.Companion.updateFoodItemFromIntent(dataItem, data);
                    }

                    if (requestCode == FIND_FOOD_REQUEST_CODE) {
                        addToSelectedList(dataItem);
                        if (caloriesSearchLog == null) {
                            caloriesSearchLog = new CaloriesSearchLog();
                        }
                        caloriesSearchLog.addItemId(dataItem.getId());
                    }

                    if (requestCode == RESULT_CODE_EDITMODE) {
                        if (adapter != null) {
                            String key = selectedData.keySet().toArray(new String[selectedData.size()])[selectedRow.first];
                            List<FoodSearchData> resultData = selectedData.get(key);
                            resultData.set(selectedRow.second, dataItem);
                            adapter.notifyDataSetChanged();
                            updateFooterStats();
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "Exception while handling onActivityResult", e);
            }
        }


    }

    public void logSearchResult(String searchItem, List<FoodSearchData> result) {
        if (caloriesSearchLog == null) {
            caloriesSearchLog = new CaloriesSearchLog();
        } else {
            searchLogger.logSearchTerm(caloriesSearchLog);
            caloriesSearchLog = new CaloriesSearchLog();
        }

        ArrayList<String> foodIds = new ArrayList<>();
        if (result.size() > 0) {
            for (FoodSearchData value : result) {
                foodIds.add(value.getId());
            }

            caloriesSearchLog.setResultFoods(foodIds);
        }

        caloriesSearchLog.setSearchTerm(searchItem);
    }

    public void addItemIDToSearchLog(String itemID) {
        if (caloriesSearchLog == null) {
            return;
        }
        if (caloriesSearchLog != null) {
            caloriesSearchLog.addItemId(itemID);
        }
    }

    public void removeItemIDFromSearchLog(String itemId) {
        if (caloriesSearchLog == null) {
            return;
        }
        if (caloriesSearchLog != null) {
            caloriesSearchLog.removeItemId(itemId);
        }
    }



}
