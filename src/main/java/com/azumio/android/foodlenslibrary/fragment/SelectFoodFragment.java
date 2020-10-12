package com.azumio.android.foodlenslibrary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;

import java.util.ArrayList;
import java.util.Arrays;


public class SelectFoodFragment extends BaseFragment implements View.OnClickListener, OnSearchListener
{
	private static final String LOG_TAG = SelectFoodFragment.class.getSimpleName();
	private ArrayList<String> mArrayValues;
	ListView mMainMenu = null;
	private int mSelectedPosition;
	String mFoodType;

	public String getFoodType()
	{
		return mFoodType;
	}

	public void setFoodType(String foodType)
	{
		this.mFoodType = foodType;
	}

	public int getSelectedPosition()
	{
		return mSelectedPosition;
	}

	public void setSelectedPosition(int selectedPosition)
	{
		this.mSelectedPosition = selectedPosition;
	}

	@Override
	public void onClick(View view)
	{

	}

	@Override
	public void onSearchTextChange(String text)
	{

	}

	@Override
	public void onSearchTextDone(String text)
	{

	}

	@Override
	public void onMealTypeChanged(String type)
	{

	}

	@Override
	public void onViewVisibility(boolean isVisible)
	{

	}

	@Override
	public void onRemovedItem(FoodSearchData foodItem)
	{

	}

	@Override
	public void onRefreshPremiumStatus()
	{

	}

	@Override
	public void clearSearch(String text)
	{

	}

	public interface AdapterListener
	{
		public void onItemClick(int position, String value);
	}

	private AdapterListener mAdpaterListerner;

	public AdapterListener getAdpaterListerner()
	{
		return mAdpaterListerner;
	}

	public void setAdapterListener(AdapterListener adpaterListerner)
	{
		this.mAdpaterListerner = adpaterListerner;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View mView = inflater.inflate(R.layout.fragment_select_food, null);
		String mealType[] = new String[4];
		for (int i = 0; i < CaloriesManager.MEAL_ORDER.length; i++)
		{
			mealType[i] = CaloriesManager.MEAL_ORDER[i].toUpperCase().charAt(0) + CaloriesManager.MEAL_ORDER[i].substring(1);
		}
		mArrayValues = new ArrayList<>(Arrays.asList(mealType));
		mMainMenu = (ListView) mView.findViewById(R.id.lv_select);
		LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.listSelect);
		linearLayout.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction().remove(SelectFoodFragment.this).commit());
		mMainMenu.setAdapter(new SearchFoodAdapter(getSelectedPosition()));
		return mView;
	}

	class SearchFoodAdapter extends BaseAdapter
	{
		private LayoutInflater inflater = null;
		private int selectedPosition;

		SearchFoodAdapter(int pos)
		{
			this.selectedPosition = pos;
		}

		@Override
		public int getCount()
		{
			return mArrayValues.size();
		}

		@Override
		public Object getItem(int arg0)
		{
			return mArrayValues.get(arg0);
		}

		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}

		class ViewHolder
		{
			TextView foodType;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			ViewHolder holder;
			if (convertView == null)
			{
				inflater = LayoutInflater.from(getActivity());
				view = inflater.inflate(R.layout.cell_food_type, null);
				holder = new ViewHolder();
				holder.foodType = (TextView) view.findViewById(R.id.foot_type);
				view.setTag(holder);
			}
			else { holder = (ViewHolder) view.getTag(); }

			holder.foodType.setText(mArrayValues.get(position));
			if (position == selectedPosition)
			{
				holder.foodType.setTextColor(ContextCompat.getColor(getParent(), R.color.calories_color));
			}
			else
			{
				holder.foodType.setTextColor(ContextCompat.getColor(getParent(), R.color.edit_calories));
			}
			holder.foodType.setBackgroundResource(R.drawable.ic_cell_gradient_middle_white);
			if (position == 0)
			{
				holder.foodType.setBackgroundResource(R.drawable.ic_cell_gradient_top);
			}
			if (position == 3)
			{
				holder.foodType.setBackgroundResource(R.drawable.ic_cell_gradient_bottom_white);
			}
			view.setOnClickListener(new OnItemClickListener(position));
			return view;
		}
	}

	private class OnItemClickListener implements View.OnClickListener
	{
		private int mPosition;

		OnItemClickListener(int position)
		{
			mPosition = position;
		}

		@Override
		public void onClick(View v)
		{
			mAdpaterListerner.onItemClick(mPosition, mArrayValues.get(mPosition));
			getActivity().getSupportFragmentManager().beginTransaction().remove(SelectFoodFragment.this).commit();
		}
	}
}
