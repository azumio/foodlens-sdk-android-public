package com.azumio.android.foodlenslibrary.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.activity.AddFoodActivity;
import com.azumio.android.foodlenslibrary.activity.EditEntryActivity;
import com.azumio.android.foodlenslibrary.api.APIClient;
import com.azumio.android.foodlenslibrary.api.FoodLensService;
import com.azumio.android.foodlenslibrary.common.CalorieFoodItemWrapper;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.model.FoodSearchResponse;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Response;

import static com.azumio.android.foodlenslibrary.utils.ContextUtils.isNotFinishing;


public class SearchRecentFragment extends BaseFragment implements OnSearchListener
{
	private static final String LOG_TAG = SearchRecentFragment.class.getSimpleName();
	private static int RESULT_CODE = 1005;
	protected static int RECENT_GROUP_POSITION = 0;
	protected static int SEARCH_GROUP_POSITION = 1;
	private ExpandableListView mMainView;
	protected RecentAdapter mAdapter;
	protected List<List<FoodSearchData>> mFoodSearchDataList;
	private List<FoodSearchData> mRecentData = new ArrayList<>();
	private View mView;
	private String mSearchText = "";
	private ServiceConnection mServiceConnection;

	private Context mApplicationContext;
	private Pair<Integer, Integer> mSelectedRow;
	private AddFoodActivity mFoodActivity;

	protected void updateData(List<FoodSearchData> data, int position)
	{
		if (!isNotFinishing(getActivity()))
		{
			return;
		}

		if (mFoodSearchDataList == null)
		{
			mFoodSearchDataList = new ArrayList<>();
		}
		if (mFoodSearchDataList.size() <= position)
		{
			mFoodSearchDataList.add(position, data);
		}
		else
		{
			mFoodSearchDataList.set(position, data);
		}

		if (mAdapter != null)
		{
			mAdapter.setData(mFoodSearchDataList);
			mAdapter.notifyDataSetChanged();
		}
		else
		{
			refreshList();
		}

		if (mMainView == null)
		{
			return;
		}

		if (mMainView != null)
		{
			if (mFoodSearchDataList.size() > 0)
			{
				mMainView.expandGroup(RECENT_GROUP_POSITION);
			}
			if (mFoodSearchDataList.size() > 1)
			{
				mMainView.expandGroup(SEARCH_GROUP_POSITION);
			}
		}
	}



	private List<FoodSearchData> getRecent(String type)
	{
		List<FoodSearchData> recentEntries = new ArrayList<>();
		List<String> recentIds = new ArrayList<>();
		/* todo jason
		for (CheckIn checkIn : mRecentCheckIn)
		{
			List<FoodSearchData> allFoodItems = parseCheckIn(checkIn);
			for (FoodSearchData foodData : allFoodItems)
			{
				if (foodData.getMeal() == null)
				{
					foodData.setMeal(CaloriesManager.MEAL_TYPE_BREAKFAST);
				}
				if (!(foodData.getType() == null ? CaloriesManager.LOG_TYPE_FOOD : foodData.getType()).equalsIgnoreCase(CaloriesManager.LOG_TYPE_QUICK))
				{
					if (foodData.getMeal().equalsIgnoreCase(type))
					{
						String parentId = "";
						if (foodData.getParentId() != null)
						{
							parentId = foodData.getParentId();
						}
						else if (foodData.getId() != null)
						{
							parentId = foodData.getId();
						}
						if (!recentIds.contains(parentId))
						{
							if (foodData.getParentId() != null)
							{
								String mParentId = foodData.getParentId();
								foodData.setParentId(null);
								foodData.setId(mParentId);
								foodData.setNutritionParsed(true);
							}
							recentEntries.add(foodData);
							recentIds.add(parentId);
							if (recentEntries.size() > 10)
							{
								return recentEntries;
							}
						}
					}
				}
			}
		}
		*/
		recentIds = null;
		return recentEntries;
	}

	public void loadData(final loadDataInterface listner)
	{
		//new GetRecentCheckIn(listner).executeOnExecutor(API.getInstance().mHTTPAsyncTasksExecutor); todo jason
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mView = inflater.inflate(R.layout.foodlens_fragment_search_recent, container, false);
		mMainView = mView.findViewById(R.id.recent_list_view);
		View footerView = ((LayoutInflater) getParent().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.foodlens_calories_footer_layout, null, false);
		mMainView.addFooterView(footerView);
		mApplicationContext = getActivity().getApplicationContext();
		mFoodActivity = (AddFoodActivity) getActivity();
		Toast.makeText(getActivity(), "foodlens side branch", Toast.LENGTH_SHORT).show();

		int flags = Context.BIND_AUTO_CREATE | Context.BIND_ABOVE_CLIENT | Context.BIND_IMPORTANT;

		if (((AddFoodActivity) getActivity()).searchView.getQuery().length() > 0)
		{
			mSearchText = ((AddFoodActivity) getActivity()).searchView.getQuery().toString();
		}
		else
		{
			mSearchText = "";
		}

		mMainView.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
		mMainView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) ->
				{

					if (((AddFoodActivity) getParent()).fragment.isVisible())
					{
						getActivity().getSupportFragmentManager().beginTransaction().remove(((AddFoodActivity) getParent()).fragment).commit();
					}
					mSelectedRow = new Pair<>(groupPosition, childPosition);
					FoodSearchData foodItem = (FoodSearchData) mAdapter.getChild(groupPosition, childPosition);
					if (foodItem != null)
					{
						String mType = foodItem.getType() == null ? "" : foodItem.getType();
						if (mType.equalsIgnoreCase(getString(R.string.foodlens_no_result)))
						{
							return true;
						}

						Intent intent = new Intent(getParent(), EditEntryActivity.class);
						intent.putExtra(CaloriesManager.PROPERTY_TYPE, (foodItem.getType() == null ? CaloriesManager.LOG_TYPE_FOOD : foodItem.getType()));
						intent.putExtra(CaloriesManager.PROPERTY_ID, mFoodSearchDataList.get(groupPosition).get(childPosition).getId());
						try
						{
							intent.putExtra(CaloriesManager.PROPERTY_DATA, foodItem.jsonString());
						}
						catch (Exception e)
						{
							Log.e(LOG_TAG, "JsonProcessingException on child click", e);
						}

						startActivityForResult(intent, RESULT_CODE);
					}

					return true;
				}

		);

		if (mFoodSearchDataList == null)
		{
			updateData(new ArrayList<>(), RECENT_GROUP_POSITION);
			updateData(new ArrayList<>(), SEARCH_GROUP_POSITION);
		}

		loadData(null);

		return mView;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		if (mFoodSearchDataList != null)
		{
			if (mFoodSearchDataList.size() > 0)
			{
				refreshList();
				if(mMainView != null)
				{
					if (mFoodSearchDataList.size() > 0)
					{
						mMainView.expandGroup(RECENT_GROUP_POSITION);
					}
					if (mFoodSearchDataList.size() > 1)
					{
						mMainView.expandGroup(SEARCH_GROUP_POSITION);
					}
				}
			}
		}
	}

	private void refreshList()
	{
		mAdapter = new RecentAdapter(mFoodSearchDataList, false);
		mAdapter.setSearchText(this.mSearchText);
		if (mMainView == null && mView != null)
		{
			mMainView = mView.findViewById(R.id.recent_list_view);
		}
		if (mMainView != null)
		{
			mMainView.setAdapter(mAdapter);
		}
	}

	private List<FoodSearchData> getFilteredRecentWithSearchText()
	{
		List<FoodSearchData> returnList = new ArrayList<>();
		if (mSearchText.length() > 0)
		{
			for (FoodSearchData dx : mRecentData)
			{
				if ((dx.getName() == null ? "" : dx.getName()).toUpperCase().contains(mSearchText.toUpperCase()))
				{
					returnList.add(dx);
				}
				else if ((dx.getBrand() == null ? "" : dx.getBrand()).contains(mSearchText.toLowerCase()))
				{
					returnList.add(dx);
				}
			}
			return returnList;
		}
		else
		{
			return mRecentData;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == RESULT_CODE)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				FoodSearchData dataItem = new FoodSearchData();

				try
				{
					if (intent.getStringExtra(CaloriesManager.PROPERTY_DATA) != null)
					{

						Gson gson = new Gson();
						String gsonString =  intent.getStringExtra(CaloriesManager.PROPERTY_DATA);
						dataItem = gson.fromJson(gsonString,FoodSearchData.class);
						FoodSearchData.Companion.updateFoodItemFromIntent(dataItem, intent);
					}
					if (mAdapter != null)
					{
						List<List<FoodSearchData>> selectedValue = new ArrayList<>(mAdapter.getData());
						if (mSelectedRow != null)
						{
							ArrayList<FoodSearchData> dataArrayList = new ArrayList(selectedValue.get(mSelectedRow.first));
							dataArrayList.set(mSelectedRow.second, dataItem);
							selectedValue.set(mSelectedRow.first, dataArrayList);
						}
						mFoodSearchDataList = selectedValue;
					}

					mFoodActivity.addToSelectedList(dataItem);
					mFoodActivity.addItemIDToSearchLog(dataItem.getId());

				}

				catch (Exception e)
				{
					Log.e(LOG_TAG, "Exception while handling onactivity result : ", e);
				}
			}
		}
	}

	@Override
	public void onSearchTextChange(String text)
	{
		this.mSearchText = text;
		updateData(getFilteredRecentWithSearchText(), RECENT_GROUP_POSITION);
	}

	@Override
	public void onSearchTextDone(final String searchText)
	{
		this.mSearchText = searchText;
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
			mAdapter.setSearchText(searchText);
			mAdapter.notifyDataSetChanged();
			FoodLensService apiService = APIClient.INSTANCE.createService(FoodLensService.class);
			BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO(), CoroutineStart.DEFAULT, (coroutineScope, continuation) -> apiService.searchFood(searchText, new Continuation<Response<FoodSearchResponse>>() {
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
									mFoodActivity.logSearchResult(searchText, response.body().getResults());
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

	@Override
	public void onMealTypeChanged(String type)
	{
		loadData(() -> updateData(getFilteredRecentWithSearchText(), RECENT_GROUP_POSITION));
	}

	@Override
	public void onViewVisibility(boolean showHide)
	{
		if (showHide)
		{
			mMainView.setVisibility(View.VISIBLE);
		}
		else
		{
			mMainView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onRemovedItem(FoodSearchData foodItem)
	{
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onRefreshPremiumStatus()
	{

	}

	@Override
	public void clearSearch(String text)
	{
		this.mSearchText = text;
		updateData(getFilteredRecentWithSearchText(), RECENT_GROUP_POSITION);
	}

	interface loadDataInterface
	{
		public void onComplete();
	}






	public class RecentAdapter extends BaseExpandableListAdapter
	{
		List<List<FoodSearchData>> mFoodSearchData;
		boolean isSearching;
		String searchText;

		RecentAdapter(List<List<FoodSearchData>> foodSearchDataList, boolean searching)
		{
			mFoodSearchData = foodSearchDataList;
			isSearching = searching;
		}

		public void setData(List<List<FoodSearchData>> data)
		{
			mFoodSearchData = data;
		}

		public List<List<FoodSearchData>> getData()
		{
			return mFoodSearchData;
		}

		public void setSearchText(String text)
		{
			this.searchText = text;
		}

		public Object getChild(int groupPosition, int childPosition)
		{
			if (isSearching)
			{
				return null;
			}
			else
			{
				return mFoodSearchData.get(groupPosition).get(childPosition);
			}
		}


		public long getChildId(int groupPosition, int childPosition)
		{
			return childPosition;
		}

		public int getChildrenCount(int groupPosition)
		{
			if (isSearching && groupPosition == SEARCH_GROUP_POSITION)
			{
				return 1;
			}
			else
			{
				return mFoodSearchData.get(groupPosition).size();
			}
		}

		public Object getGroup(int groupPosition)
		{
			return null;
		}

		public int getGroupCount()
		{
			return mFoodSearchData.size();
		}

		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
		{
			if (mFoodSearchData.get(groupPosition).size() < 1)
			{
				if (groupPosition == RECENT_GROUP_POSITION)
				{
					return new View(getActivity());
				}
				else if (!isSearching)
				{
					return new View(getActivity());
				}
			}

			View row = convertView;
			if (row == null)
			{
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				row = inflater.inflate(R.layout.foodlens_cell_group_recent_list, null);
			}
			else if (row.findViewById(R.id.cell_recent_lunch_type) == null)
			{
				LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.foodlens_cell_group_recent_list, null);
			}

			TextView title = (TextView) row.findViewById(R.id.cell_recent_lunch_type);
			RelativeLayout verifiedLayout = (RelativeLayout) row.findViewById(R.id.verifiedLayout);
			verifiedLayout.setVisibility(View.GONE);
			if (groupPosition == RECENT_GROUP_POSITION)
			{
				title.setText(getString(R.string.foodlens_my_recent) + " " + ((AddFoodActivity) getParent()).getType().toUpperCase());
			}
			else
			{
				if ( this.searchText.length() > 0)
				{
					if(isSearching) {
						title.setText(getString(R.string.foodlens_searching_for) + " " + this.searchText);
					}
					else
					{
						title.setText(getString(R.string.foodlens_search_results) + " " + this.searchText);
					}
				}

				else
				{
					title.setText("");
				}
			}
			return row;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}

		public boolean hasStableIds()
		{
			return true;
		}

		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
		{
			View row = convertView;
			CalorieFoodItemWrapper wrapper = null;

			if (row == null)
			{
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				row = inflater.inflate(R.layout.foodlens_cell_child_recent_list, null);
				wrapper = new CalorieFoodItemWrapper(row, getActivity(), true);
				wrapper.setIItemCheckChangedListener((compoundButton, b, foodItem) ->
				{
					if (b)
					{
						mFoodActivity.addToSelectedList(foodItem);
						mFoodActivity.addItemIDToSearchLog(foodItem.getId());
					}
					else
					{
						mFoodActivity.removeFromSelectedList(foodItem.getId());
						mFoodActivity.removeItemIDFromSearchLog(foodItem.getId());
					}
				});
				row.setTag(wrapper);
			}
			else
			{
				wrapper = (CalorieFoodItemWrapper) row.getTag();
			}

			RelativeLayout progressLayout = (RelativeLayout) row.findViewById(R.id.progressLayout);
			RelativeLayout foodLayout = (RelativeLayout) row.findViewById(R.id.foodLayout);
			RelativeLayout mNoresult = (RelativeLayout) row.findViewById(R.id.no_result);
			mNoresult.setVisibility(View.GONE);
			progressLayout.setVisibility(View.GONE);

			if (isSearching && groupPosition == SEARCH_GROUP_POSITION)
			{
				progressLayout.setVisibility(View.VISIBLE);
				foodLayout.setVisibility(View.GONE);
			}
			else
			{
				wrapper.setChecked(mFoodActivity.containsFoodItem(mFoodSearchData.get(groupPosition).get(childPosition), mFoodActivity.getType()));
				foodLayout.setVisibility(View.VISIBLE);
				String mType = mFoodSearchData.get(groupPosition).get(childPosition).getType() == null ? "" : mFoodSearchData.get(groupPosition).get(childPosition).getType();
				if (mType.equalsIgnoreCase(getString(R.string.foodlens_no_result)))
				{
					mNoresult.setVisibility(View.VISIBLE);
					foodLayout.setVisibility(View.GONE);
				}
				else
				{
					wrapper.populateFrom(mFoodSearchData.get(groupPosition).get(childPosition));
				}
			}

			row.setBackgroundResource(R.drawable.foodlens_ic_cell_gradient_middle);
			return (row);
		}
	}
}
