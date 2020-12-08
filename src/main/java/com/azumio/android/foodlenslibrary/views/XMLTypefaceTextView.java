package com.azumio.android.foodlenslibrary.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.utils.TextUtils;


public class XMLTypefaceTextView extends AppCompatTextView
{
	public XMLTypefaceTextView(Context context)
	{
		super(context);
		initialize(context, null, 0, 0);
	}

	public XMLTypefaceTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize(context, attrs, 0, 0);
	}

	public XMLTypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initialize(context, attrs, defStyleAttr, 0);
	}

	public XMLTypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr);
		initialize(context, attrs, defStyleAttr, defStyleRes);
	}

	public void setTypefaceFromAssets(Context context, String customTypeface)
	{
		if (customTypeface != null)
		{
			Typeface typeface = TextUtils.loadTypefaceFromAssets(context, customTypeface);

			if (typeface != null)
			{
				setTypeface(typeface);
			}
		}
	}

	public void setTypefaceFromAssetsWithStyle(Context context, String customTypeface, int style)
	{
		if (customTypeface != null)
		{
			Typeface typeface = TextUtils.loadTypefaceFromAssets(context, customTypeface);

			if (typeface != null)
			{
				setTypeface(typeface, style);
			}
		}
	}

	private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		if (isInEditMode() || attrs == null)
		{
			return;
		}
		String customTypeface;
		final Resources.Theme theme = context.getTheme();
		TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.FoodLensXMLCustomTypefaceTextView, defStyleAttr, defStyleRes);
		// DO NOT CHANGE THIS LINE - by default we should use system default font - no hacks please!
		customTypeface = a.getString(R.styleable.FoodLensXMLCustomTypefaceTextView_foodlens_typeface);

		a.recycle();
		setTypefaceFromAssets(context, customTypeface);
	}
}
