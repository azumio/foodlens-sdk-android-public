package com.azumio.android.foodlenslibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;

import java.util.List;


public class ServingDialog extends DialogFragment
{

	public interface OnServingInterface
	{
		public void servingSet(SegmentResponse.FoodItem.ServingSize servingSizeData, Double servingCount);

		public void servingCancel();
	}

	private List<SegmentResponse.FoodItem.ServingSize> mServingSizeDataList;
	protected OnServingInterface mServingListner;

	private SegmentResponse.FoodItem.ServingSize mSelectedServingSize;
	private double mSelectedNumberOfServing;

	private String mSelectedServingUnit;

	public void setSelectedNumberOfServing(double selectedNumberOfServing)
	{
		this.mSelectedNumberOfServing = selectedNumberOfServing;
	}

	public void setSelectedServingUnit(String selectedServingUnit)
	{
		this.mSelectedServingUnit = selectedServingUnit;
	}

	private Spinner mServingCountSpinner;
	private EditText mServingCountText;

	public void setServingListner(OnServingInterface servingListner)
	{
		mServingListner = servingListner;
	}

	public List<SegmentResponse.FoodItem.ServingSize> getServingSizeDataList()
	{
		return mServingSizeDataList;
	}

	public void setServingSizeDataList(List<SegmentResponse.FoodItem.ServingSize> servingSizeDataList)
	{
		mServingSizeDataList = servingSizeDataList;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		View mView = inflater.inflate(R.layout.foodlens_cell_serving_view, null);
		SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getActivity(), R.layout.foodlens_custom_spinner_item, mServingSizeDataList);

		SetTextWatcher textWatcher = new SetTextWatcher();
		mServingCountText = (EditText) mView.findViewById(R.id.cell_numberserving_count);
		mServingCountText.setText(String.valueOf(mSelectedNumberOfServing));
		mServingCountText.addTextChangedListener(textWatcher);
		mServingCountSpinner = (Spinner) mView.findViewById(R.id.cell_serving_count);
		mServingCountSpinner.setAdapter(spinnerAdapter);
		mServingCountSpinner.setSelection(getPositionForCurrentServingUnit());
		mSelectedServingSize = getServingSizeDataList().get(0);

		mServingCountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			int count = 0;

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long l)
			{
				if (count >= 1)
				{
					mSelectedServingSize = getServingSizeDataList().get(pos);
				}
				count++;
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView)
			{

			}
		});
		getDialog().setTitle(getString(R.string.foodlens_how_much));
		final Button Set = (Button) mView.findViewById(R.id.buttonSet);
		final Button Cancel = (Button) mView.findViewById(R.id.buttonCancel);

		Set.setOnClickListener(v -> {
			mServingListner.servingSet(mSelectedServingSize, mSelectedNumberOfServing);
			dismiss();
		});

		Cancel.setOnClickListener(v -> {
			mServingListner.servingCancel();
			dismiss();

		});

		mServingCountText.requestFocus();
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		return mView;
	}


	public int getPositionForCurrentServingUnit()
	{
		for (SegmentResponse.FoodItem.ServingSize ss : mServingSizeDataList)
		{
			if (ss.getUnit().equalsIgnoreCase(mSelectedServingUnit))
			{
				return mServingSizeDataList.indexOf(ss);
			}
		}
		return 0;
	}

	class SetTextWatcher implements TextWatcher
	{

		@Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
		{

		}

		@Override
		public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
		{

		}

		@Override
		public void afterTextChanged(Editable editable)
		{
			if (mServingCountText.getText().toString().matches("^[0-9.]+$"))
			{
				if (mServingCountText.getText().toString().contains("."))
				{
					if (mServingCountText.getText().length() > 1) { mSelectedNumberOfServing = Double.valueOf(mServingCountText.getText().toString()); }
				}
				else { mSelectedNumberOfServing = Double.valueOf(mServingCountText.getText().toString()); }
			}
			else { mSelectedNumberOfServing = CaloriesManager.NUMBER_OF_SERVINGS; }
		}
	}

	public class SpinnerAdapter extends ArrayAdapter<SegmentResponse.FoodItem.ServingSize>
	{

		private List<SegmentResponse.FoodItem.ServingSize> mSizeDatas;
		private Context context;

		public SpinnerAdapter(Context context, int resourceId, List<SegmentResponse.FoodItem.ServingSize> objects)
		{
			super(context, resourceId, objects);
			this.mSizeDatas = objects;
			this.context = context;
		}

		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent)
		{
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			View row = inflater.inflate(R.layout.foodlens_custom_spinner_item, parent, false);
			TextView label = (TextView) row.findViewById(R.id.item);
			label.setText(mSizeDatas.get(position).getUnit());
			return row;
		}
	}
}
