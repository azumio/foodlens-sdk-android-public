package com.azumio.android.foodlenslibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.utils.ViewUtils;


public class ViewPagerTabView extends LinearLayout
{
	private static final int DEFAULT_VALUE = -1;
	private static final float MIN_ALPHA = 0.3f;
	private static final float DEFAULT_TEXT_SIZE_SP = 13.8f;
	private ViewPager viewPager;
	private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

	private Paint selectedIndicatorPaint;
	private int selectedIndicatorThickness;

	private int selectedPosition;
	private float selectionOffset;
	private int textColor;
	private int textSize;
	private int oldSelectedPosition = DEFAULT_VALUE;
	private boolean textAllCaps = true;

	public ViewPagerTabView(Context context)
	{
		super(context, null);
		init(context, null, 0);
	}

	public ViewPagerTabView(Context context, AttributeSet attrs)
	{
		super(context, attrs, 0);
		init(context, attrs, 0);
	}

	public ViewPagerTabView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	private void init(Context context, AttributeSet attrs, int defStyleAttr)
	{
		setWillNotDraw(false);
		selectedIndicatorPaint = new Paint();
		loadXmlVariables(context, attrs, defStyleAttr);
	}

	private void loadXmlVariables(Context context, AttributeSet attrs, int defStyleAttr)
	{
		textSize = (int) (getResources().getDisplayMetrics().scaledDensity * DEFAULT_TEXT_SIZE_SP);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FoodLensViewPagerTabView, defStyleAttr, 0);
		int indicatorColor = typedArray.getColor(R.styleable.FoodLensViewPagerTabView_foodlens_indicator_color, Color.BLACK);
		int indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.FoodLensViewPagerTabView_foodlens_indicator_height, 25);
		textColor = typedArray.getColor(R.styleable.FoodLensViewPagerTabView_foodlens_tab_text_color, Color.BLACK);
		textSize = typedArray.getDimensionPixelSize(R.styleable.FoodLensViewPagerTabView_foodlens_tab_text_size, textSize);
		textAllCaps = typedArray.getBoolean(R.styleable.FoodLensViewPagerTabView_foodlens_text_all_caps, true);
		typedArray.recycle();

		selectedIndicatorPaint.setColor(indicatorColor);
		selectedIndicatorThickness = indicatorHeight;
	}

	public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener)
	{
		viewPagerPageChangeListener = listener;
	}

	public void setViewPager(ViewPager viewPager)
	{
		this.removeAllViews();

		this.viewPager = viewPager;
		if (viewPager != null)
		{
			viewPager.setOnPageChangeListener(new InternalViewPagerListener());
			insertData();
		}
	}

	public void setTextForTab(int tabPosition, CharSequence title)
	{
		if (tabPosition < 0 || tabPosition > getChildCount())
		{
			return;
		}

		View view = getChildAt(tabPosition);

		if (view instanceof TextView)
		{
			TextView tab = (TextView) view;
			tab.setGravity(Gravity.LEFT);
			tab.setText(title);
		}
	}

	private void insertData()
	{
		final PagerAdapter adapter = viewPager.getAdapter();
		final OnClickListener tabClickListener = new TabClickListener();

		for (int i = 0; i < adapter.getCount(); i++)
		{
			View tabView;
			CharSequence title = adapter.getPageTitle(i);
			//TODO use polymorphism
			boolean hasTabIcons = false; //getContext().getResources().getBoolean(R.bool.hasIconsInTab);
			tabView = hasTabIcons ? createTabViewForIcons(getContext(), title, textColor) : createDefaultTabViewForText(getContext(), title, textColor);

			tabView.setOnClickListener(tabClickListener);

			this.addView(tabView);
		}

		int selectedPosition = viewPager.getCurrentItem();
		if (viewPager.getCurrentItem() < 0 || viewPager.getCurrentItem() > this.getChildCount())
		{
			selectedPosition = 0;
		}

		setSelected(selectedPosition);
	}

	private void setSelected(int selectedPosition)
	{
		this.getChildAt(this.selectedPosition).setSelected(false);
		this.getChildAt(selectedPosition).setSelected(true);
		this.getChildAt(this.selectedPosition).setAlpha(MIN_ALPHA);
		this.getChildAt(selectedPosition).setAlpha(1.0f);
		invalidate();
	}

	protected XMLTypefaceTextView createDefaultTabViewForText(Context context, CharSequence title, int color)
	{
		XMLTypefaceTextView textView = new XMLTypefaceTextView(context);
		textView.setGravity(Gravity.CENTER);
		textView.setTypeface(null, Typeface.BOLD);

		textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
		textView.setAllCaps(textAllCaps);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		textView.setAlpha(MIN_ALPHA);

		textView.setText(title);
		textView.setTextColor(color);

		return textView;
	}

	protected CenteredCustomFontView createTabViewForIcons(Context context, CharSequence pageTitle, int textColor)
	{
		CenteredCustomFontView textView = new CenteredCustomFontView(context);
		textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

		textView.setTextSize(getResources().getDimensionPixelOffset(R.dimen.foodlens_viewpager_textsize));

		textView.setAlpha(MIN_ALPHA);


		textView.setText(pageTitle);
		textView.setTextColor(textColor);
		return textView;
	}

	@Override
	public void addView(View child)
	{
		ViewUtils.setWeightToViewVertical(child, 1);
		super.addView(child);
	}

	void onViewPagerPageChanged(int position, float positionOffset)
	{
		selectedPosition = position;
		selectionOffset = positionOffset;
		invalidate();
	}

	public void setTextColor(int textColor)
	{
		this.textColor = textColor;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			View child = getChildAt(i);
			if (child instanceof TextView)
			{
				((TextView) child).setTextColor(textColor);
			}
		}
	}

	public void setSelectedIndicatorColor(int selectedIndicatorColor)
	{
		this.selectedIndicatorPaint.setColor(selectedIndicatorColor);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		float restAlpha = 1 - MIN_ALPHA;

		final int height = getHeight();
		final int childCount = getChildCount();

		// Thick colored underline below the current selection
		if (childCount > 0)
		{
			View selectedTitle = getChildAt(selectedPosition);
			int left = selectedTitle.getLeft();
			int right = selectedTitle.getRight();

			if (selectionOffset > 0f && selectedPosition < (getChildCount() - 1))
			{
				// Draw the selection partway between the tabs
				View nextTitle = getChildAt(selectedPosition + 1);

				selectedTitle.setAlpha((1 - selectionOffset) * restAlpha + MIN_ALPHA);
				nextTitle.setAlpha(selectionOffset * restAlpha + MIN_ALPHA);

				left = (int) (selectionOffset * nextTitle.getLeft() + (1.0f - selectionOffset) * left);
				right = (int) (selectionOffset * nextTitle.getRight() + (1.0f - selectionOffset) * right);

				changeAlphaForPreviousSelectedItems();
			}
			else
			{
				changeAlphaForPreviousSelectedItems();

				for (int i = 0; i < getChildCount(); i++)
				{
					View current = getChildAt(i);

					float alpha = selectedPosition == i ? 1.0f : MIN_ALPHA;
					current.setAlpha(alpha);
				}
			}

			canvas.drawRect(left, height - selectedIndicatorThickness, right, height, selectedIndicatorPaint);
		}
	}

	private void changeAlphaForPreviousSelectedItems()
	{
		if (oldSelectedPosition == DEFAULT_VALUE)
		{
			oldSelectedPosition = selectedPosition;
		}

		if (oldSelectedPosition != selectedPosition)
		{
			View previousView = getChildAt(oldSelectedPosition);

			if (previousView != null)
			{
				previousView.setAlpha(MIN_ALPHA);
			}

			oldSelectedPosition = selectedPosition;
		}
	}

	private class InternalViewPagerListener implements ViewPager.OnPageChangeListener
	{

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
		{
			int tabStripChildCount = ViewPagerTabView.this.getChildCount();
			if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount))
			{
				return;
			}

			ViewPagerTabView.this.onViewPagerPageChanged(position, positionOffset);

			if (viewPagerPageChangeListener != null)
			{
				viewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state)
		{
			if (viewPagerPageChangeListener != null)
			{
				viewPagerPageChangeListener.onPageScrollStateChanged(state);
			}
		}

		@Override
		public void onPageSelected(int position)
		{
			for (int i = 0; i < ViewPagerTabView.this.getChildCount(); i++)
			{
				ViewPagerTabView.this.getChildAt(i).setSelected(position == i);
			}
			if (viewPagerPageChangeListener != null)
			{
				viewPagerPageChangeListener.onPageSelected(position);
			}
		}
	}

	private class TabClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			for (int i = 0; i < ViewPagerTabView.this.getChildCount(); i++)
			{
				if (v == ViewPagerTabView.this.getChildAt(i))
				{
					viewPager.setCurrentItem(i);

					setSelected(i);
					return;
				}
			}
		}
	}
}
