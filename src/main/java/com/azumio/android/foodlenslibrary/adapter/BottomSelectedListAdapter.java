package com.azumio.android.foodlenslibrary.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.common.CalorieFoodItemWrapper;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BottomSelectedListAdapter extends BaseExpandableListAdapter
{
	Activity mActivity;
	Map<String, List<FoodSearchData>> mAdapterData;
	List<String> mKeys;

	public interface OnCheckChangeListerner
	{
		void onCheckChange(boolean isChecked, FoodSearchData foodSearchData);
	}

	public void setonCheckChangelisterner(OnCheckChangeListerner onCheckChangelisterner)
	{
		this.mOnCheckChangelisterner = onCheckChangelisterner;
	}

	OnCheckChangeListerner mOnCheckChangelisterner;


	public BottomSelectedListAdapter(Map<String, List<FoodSearchData>> foodSearchDataList, Activity activity, boolean isIngredientMode)
	{
		mAdapterData = foodSearchDataList;
		mActivity = activity;
		mKeys = new ArrayList<>();
		if (isIngredientMode)
		{
			mKeys.add(mActivity.getString(R.string.foodlens_ingredients));
		}
		else
		{
			for (int i = 0; i < CaloriesManager.MEAL_ORDER.length; i++)
			{
				if (mAdapterData != null)
				{
					if (mAdapterData.keySet().contains(CaloriesManager.MEAL_ORDER[i]))
					{
						mKeys.add(CaloriesManager.MEAL_ORDER[i]);
					}
				}
			}
		}
	}

	public Object getChild(int groupPosition, int childPosition)
	{
		String key = mKeys.get(groupPosition);
		List<FoodSearchData> data =  mAdapterData.get(key);
		return data.get(childPosition);
	}


	public void setData(Map<String, List<FoodSearchData>> data)
	{
		mAdapterData = data;
	}

	public Map<String, List<FoodSearchData>> getData()
	{
		return mAdapterData;
	}

	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	public int getChildrenCount(int groupPosition)
	{
		String key = mKeys.get(groupPosition);
		ArrayList<FoodSearchData> data = (ArrayList<FoodSearchData>) mAdapterData.get(key);
		return data.size();
	}

	public Object getGroup(int groupPosition)
	{
		String key = mKeys.get(groupPosition);
		return key;
	}

	public int getGroupCount()
	{
		if (mAdapterData != null)
		{
			return mAdapterData.size();
		}
		else { return 0; }
	}

	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		View row = convertView;
		if (row == null)
		{
			LayoutInflater inflater = LayoutInflater.from(mActivity);
			row = inflater.inflate(R.layout.foodlens_cell_group_recent_list, null);
		}

		String key = mKeys.get(groupPosition);
		TextView title = (TextView) row.findViewById(R.id.cell_recent_lunch_type);
		RelativeLayout layout = (RelativeLayout) row.findViewById(R.id.menulist);
		title.setText(key.toUpperCase());
		layout.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.foodlens_white));
		RelativeLayout verifiedLayout = (RelativeLayout) row.findViewById(R.id.verifiedLayout);
		verifiedLayout.setVisibility(View.GONE);
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

	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		View row = convertView;
		CalorieFoodItemWrapper wrapper = null;

		if (row == null)
		{
			LayoutInflater inflater = LayoutInflater.from(mActivity);
			row = inflater.inflate(R.layout.foodlens_cell_child_recent_list, null);
			wrapper = new CalorieFoodItemWrapper(row, mActivity, true);
			wrapper.setChecked(true);
			wrapper.setIItemCheckChangedListener((compoundButton, b, foodItem) -> {
				if (mOnCheckChangelisterner != null) { mOnCheckChangelisterner.onCheckChange(b, foodItem); }
			});
			row.setTag(wrapper);
		}
		else
		{
			wrapper = (CalorieFoodItemWrapper) row.getTag();
		}

		String key = mKeys.get(groupPosition);
		List<FoodSearchData> data = mAdapterData.get(key);
		wrapper.populateFrom(data.get(childPosition));
		return (row);
	}
}
