package com.azumio.android.foodlenslibrary.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.api.APIClient;
import com.azumio.android.foodlenslibrary.api.FoodLensService;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.FoodSearchResponse;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Response;

import static com.azumio.android.foodlenslibrary.activity.AddFoodActivity.ADDITIONAL_POINT_EXTRA_KEY;
import static com.azumio.android.foodlenslibrary.activity.AddFoodActivity.IMAGE_CACHE_ID_EXTRA_KEY;
import static com.azumio.android.foodlenslibrary.utils.ContextUtils.isNotFinishing;

public class SearchSuggestionFragment extends  SearchRecentFragment {

    private static final String LOG_TAG = SearchRecentFragment.class.getSimpleName();

    public String selectedPoint;
    public String imageCacheId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle =  this.getArguments();
        if (bundle.containsKey(ADDITIONAL_POINT_EXTRA_KEY))
        {
            selectedPoint = bundle.getString(ADDITIONAL_POINT_EXTRA_KEY);
        }
        if(bundle.containsKey(IMAGE_CACHE_ID_EXTRA_KEY))
        {
            imageCacheId = bundle.getString(IMAGE_CACHE_ID_EXTRA_KEY);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void loadData(loadDataInterface listner) {


        if (mAdapter == null)
        {
            if (mFoodSearchDataList != null)
            {
                mAdapter = new RecentAdapter(mFoodSearchDataList, false);
            }
            else
            {
                return;
            }
        }
        if (mAdapter != null)
        {
            mAdapter.isSearching = true;
            mAdapter.setSearchText("");
            mAdapter.notifyDataSetChanged();
            FoodLensService apiService = APIClient.INSTANCE.createService(FoodLensService.class);

            BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO(), CoroutineStart.DEFAULT, (coroutineScope, continuation) -> apiService.getSuggestions(this.selectedPoint, this.imageCacheId, new Continuation<Response<SegmentResponse>>() {
                @NotNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }
                @Override
                public void resumeWith(@NotNull Object o) {
                    try {
                        Response<FoodSearchResponse> response = ((Response<FoodSearchResponse>) o);
                        if (response.isSuccessful()) {
                            Activity mActivity = getActivity();
                            mActivity.runOnUiThread(() -> {
                                if (isNotFinishing(mActivity)) {
                                    mAdapter.isSearching = false;
                                    if (response.body().getResults().size() < 1) {
                                        FoodSearchData foodSearchData = new FoodSearchData();
                                        foodSearchData.setType(getString(R.string.foodlens_no_result));
                                        ArrayList<FoodSearchData> emptyResult = new ArrayList<FoodSearchData>();
                                        emptyResult.add(foodSearchData);
                                        updateData(emptyResult, SEARCH_GROUP_POSITION);
                                    } else {
                                        updateData(response.body().getResults(), SEARCH_GROUP_POSITION);

                                    }
                                }
                            });

                        } else {
                            Log.e(LOG_TAG, "Exception in CaloriesFoodSearchRequest failure response: " + response.errorBody().string());
                        }
                        Log.i(LOG_TAG, response.toString());
                    }
                    catch (Exception e)
                    {
                        Log.e(LOG_TAG, "Exception while handling success response: ", e);
                    }
                }
            }));
        }


    }
}

