package com.azumio.android.foodlenslibrary.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.fragment.OnSaveListener;
import com.azumio.android.foodlenslibrary.fragment.SearchQuickFragment;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap;
import com.azumio.android.foodlenslibrary.utils.CaloriesManager;
import com.azumio.android.foodlenslibrary.utils.ColorUtils;
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView;
import com.azumio.android.foodlenslibrary.views.FillingView;

import androidx.core.content.ContextCompat;


public class QuickAddActivity extends BaseFragmentActivity
{
	private static final String LOG_TAG = QuickAddActivity.class.getSimpleName();
	private FillingView mToolbar;

	@Override
	protected void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
		setContentView(R.layout.foodlens_activity_edit_quick_food);

		TextView textView = findViewById(R.id.activity_with_fragment_toolbar_textview);
		final TextView save = findViewById(R.id.activity_save);
		save.setOnClickListener(view -> {
			if (getActiveFragment() instanceof OnSaveListener) { ((OnSaveListener) getActiveFragment()).save(save, null); }
		});
		initBackArrow();
		mToolbar = findViewById(R.id.main_menu_toolbars);
		mToolbar.setVisibility(View.VISIBLE);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.foodlens_calories_color));
		ColorUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.foodlens_foodlens_calories_statusbar_color), ContextCompat.getColor(this, R.color.foodlens_calories_color));
		textView.setText(getString(R.string.foodlens_quick_add));
		Bundle extras = getIntent().getExtras();
		if (extras.getString(CaloriesManager.PROPERTY_DATA) != null)
		{
			FoodSearchData dataItem;

			try
			{
				dataItem =  FoodSearchData.Companion.initFromJson(extras.getString(CaloriesManager.PROPERTY_DATA));
				SearchQuickFragment fragment = SearchQuickFragment.newInstance();
				fragment.setDataQuickItems(dataItem);
				push(fragment);
			}
			catch (Exception e)
			{
				Log.e(LOG_TAG, "Exception while getting value from extra:  ", e);
			}
		}
	}

	private void initBackArrow()
	{
		CenteredCustomFontView arrow = findViewById(R.id.toolbar_back_arrow);
		String iconPath = this.getString(R.string.foodlens_font_path_material_design_set);
		arrow.setFontPath(iconPath);
		arrow.setText(ArgusIconMap.getInstance().get(ArgusIconMap.ARROW_LEFT));
		arrow.setOnClickListener(v -> finish());
	}
}
