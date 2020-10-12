package com.azumio.android.foodlenslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.common.drawable.TintDrawableWrapper;

import java.util.HashMap;
import java.util.Map;

public class TintDrawableHelper
{
	private static final Map<Triplet<Integer, Integer, Integer>, ColorStateList> DEFAULT_COLOR_STATE_LISTS = new HashMap<>();
	private static final Map<Triplet<Integer, Integer, Integer>, ColorStateList> SWITCH_THUMB_STATE_LISTS = new HashMap<>();
	private static final Map<Triplet<Integer, Integer, Integer>, ColorStateList> SWITCH_TRACK_STATE_LISTS = new HashMap<>();
	private Map<Integer, Integer> cachedColors;
	private Float disabledAlpha;
	private Context context;
	private TypedValue typedValue;

	public TintDrawableHelper(@NonNull Context context)
	{
		this.context = context;
		typedValue = new TypedValue();
		cachedColors = new HashMap<>();
		disabledAlpha = null;
	}

	private int getThemeAttrColor(int attr)
	{
		Integer rv = cachedColors.get(attr);
		if (rv == null)
		{
			rv = 0;
			if (context.getTheme().resolveAttribute(attr, typedValue, true))
			{
				if (typedValue.type >= TypedValue.TYPE_FIRST_INT && typedValue.type <= TypedValue.TYPE_LAST_INT)
				{
					rv = typedValue.data;
				}
				else if (typedValue.type == TypedValue.TYPE_STRING)
				{
					rv = ContextCompat.getColor(context, typedValue.resourceId);
				}
			}
			cachedColors.put(attr, rv);
		}
		return rv;
	}

	private int getColorByApplyingAlpha(int color, float alpha)
	{
		final int originalAlpha = Color.alpha(color);

		// Return the color, multiplying the original alpha by the disabled value
		return (color & 0x00ffffff) | (Math.round(originalAlpha * alpha) << 24);
	}

	private float disabledAlpha()
	{
		if (disabledAlpha == null)
		{
			context.getTheme().resolveAttribute(android.R.attr.disabledAlpha, typedValue, true);
			disabledAlpha = typedValue.getFloat();
		}
		return disabledAlpha;
	}

	public void setupToolbarBackgroundFromTheme(@NonNull Toolbar toolbar)
	{
		int toolbarColor = getThemeAttrColor(R.attr.toolbarColor);
		if (toolbar != null)
		{
			Drawable drawable = new ColorDrawable(toolbarColor);
			toolbar.setBackground(drawable);
		}
	}

	public final int getControlNormalColor()
	{
		// this value was hardcoded in multiple places (see the commit in which this method was added).
		// search reveals that FFB9B9B9 is colorControlNormal.
		// getThemeAttrColor(R.attr.colorControlNormal) returns a different value for some reason, though.
		// i have no time to investigate it, so i'm leaving it hardcoded - since it was anyway.
		return 0xFFB9B9B9;
	}

	public void setupStatusBarBackgroundFromTheme(@NonNull Activity activity)
	{
		Window window = activity.getWindow();
		if (window != null)
		{
			int statusBarColor = getThemeAttrColor(R.attr.statusBarColor);
			window.setStatusBarColor(statusBarColor);
		}
	}

	public Drawable getControlDrawable(int controlDrawableResourceId)
	{
		return getControlDrawable(null, null, null, controlDrawableResourceId, PorterDuff.Mode.SRC_IN);
	}

	public Drawable getControlDrawable(int controlDrawableResourceId, PorterDuff.Mode mode)
	{
		return getControlDrawable(null, null, null, controlDrawableResourceId, mode);
	}

	public Drawable getControlDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int controlDrawableResourceId)
	{
		return getControlDrawable(disabledColor, normalColor, activatedColor, controlDrawableResourceId, PorterDuff.Mode.SRC_IN);
	}

	public Drawable getControlDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int controlDrawableResourceId, PorterDuff.Mode mode)
	{
		Drawable drawable = ContextCompat.getDrawable(context, controlDrawableResourceId);
		if (drawable != null)
		{
			drawable = new TintDrawableWrapper(drawable, getDefaultColorStateList(disabledColor, normalColor, activatedColor), mode);
		}
		return drawable;
	}

	public Drawable getToolbarDrawable(int controlDrawableResourceId)
	{
		return getToolbarDrawable(null, null, null, controlDrawableResourceId, PorterDuff.Mode.SRC_IN);
	}

	public Drawable getToolbarDrawable(int controlDrawableResourceId, PorterDuff.Mode mode)
	{
		return getToolbarDrawable(null, null, null, controlDrawableResourceId, mode);
	}

	public Drawable getToolbarDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int controlDrawableResourceId)
	{
		return getToolbarDrawable(disabledColor, normalColor, activatedColor, controlDrawableResourceId, PorterDuff.Mode.SRC_IN);
	}

	public Drawable getToolbarDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int controlDrawableResourceId, PorterDuff.Mode mode)
	{
		Drawable drawable = ContextCompat.getDrawable(context, controlDrawableResourceId);
		if (drawable != null)
		{
			drawable = new TintDrawableWrapper(drawable, getToolbarColorStateList(disabledColor, normalColor, activatedColor), mode);
		}
		return drawable;
	}

	public Drawable getSwitchTrackDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		Drawable drawable = ContextCompat.getDrawable(context, androidx.appcompat.R.drawable.abc_switch_track_mtrl_alpha);
		if (drawable != null)
		{
			drawable = new TintDrawableWrapper(drawable, getSwitchTrackColorStateList(disabledColor, normalColor, activatedColor));
		}
		return drawable;
	}

	public Drawable getSwitchThumbDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		Drawable drawable = ContextCompat.getDrawable(context, androidx.appcompat.R.drawable.abc_switch_thumb_material);
		if (drawable != null)
		{
			drawable = new TintDrawableWrapper(drawable, getSwitchThumbColorStateList(disabledColor, normalColor, activatedColor), PorterDuff.Mode.MULTIPLY);
		}
		return drawable;
	}

	public int getDefaultDisabledColor()
	{
		return getThemeAttrColor(androidx.appcompat.R.attr.colorControlNormal);
	}

	public int getDefaultNormalColor()
	{
		return getThemeAttrColor(androidx.appcompat.R.attr.colorControlNormal);
	}

	public int getDefaultActivatedColor()
	{
		return getThemeAttrColor(androidx.appcompat.R.attr.colorControlActivated);
	}

	public ColorStateList getToolbarColorStateList()
	{
		return getToolbarColorStateList(null, null, null);
	}

	public ColorStateList getToolbarColorStateList(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		if (disabledColor == null)
		{
			disabledColor = getThemeAttrColor(R.attr.toolbarNormalColor);
		}
		if (normalColor == null)
		{
			normalColor = getThemeAttrColor(R.attr.toolbarNormalColor);
		}
		if (activatedColor == null)
		{
			activatedColor = getThemeAttrColor(R.attr.toolbarActiveColor);
			if (activatedColor == 0)
			{
				activatedColor = getDefaultActivatedColor();
			}
		}

		Triplet<Integer, Integer, Integer> key = new Triplet<>(disabledColor, normalColor, activatedColor);
		ColorStateList defaultColorStateList = DEFAULT_COLOR_STATE_LISTS.get(key);
		if (defaultColorStateList == null)
		{
			final int[][] states = new int[7][];
			final int[] colors = new int[7];
			int i = 0;

			// Disabled state
			states[i] = new int[]{-android.R.attr.state_enabled};
			colors[i] = getColorByApplyingAlpha(disabledColor, disabledAlpha());
			i++;

			states[i] = new int[]{android.R.attr.state_focused};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_activated};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_pressed};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_checked};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_selected};
			colors[i] = activatedColor;
			i++;

			// Default enabled state
			states[i] = new int[0];
			colors[i] = normalColor;
			i++;

			defaultColorStateList = new ColorStateList(states, colors);
			DEFAULT_COLOR_STATE_LISTS.put(key, defaultColorStateList);
		}
		return defaultColorStateList;
	}

	public ColorStateList getDefaultColorStateList()
	{
		return getDefaultColorStateList(null, null, null);
	}

	public ColorStateList getDefaultColorStateList(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		if (disabledColor == null)
		{
			disabledColor = getDefaultDisabledColor();
		}
		if (normalColor == null)
		{
			normalColor = getDefaultNormalColor();
		}
		if (activatedColor == null)
		{
			activatedColor = getDefaultActivatedColor();
		}

		Triplet<Integer, Integer, Integer> key = new Triplet<>(disabledColor, normalColor, activatedColor);
		ColorStateList defaultColorStateList = DEFAULT_COLOR_STATE_LISTS.get(key);
		if (defaultColorStateList == null)
		{
			final int[][] states = new int[7][];
			final int[] colors = new int[7];
			int i = 0;

			// Disabled state
			states[i] = new int[]{-android.R.attr.state_enabled};
			colors[i] = getColorByApplyingAlpha(disabledColor, disabledAlpha());
			i++;

			states[i] = new int[]{android.R.attr.state_focused};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_activated};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_pressed};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_checked};
			colors[i] = activatedColor;
			i++;

			states[i] = new int[]{android.R.attr.state_selected};
			colors[i] = activatedColor;
			i++;

			// Default enabled state
			states[i] = new int[0];
			colors[i] = normalColor;
			i++;

			defaultColorStateList = new ColorStateList(states, colors);
			DEFAULT_COLOR_STATE_LISTS.put(key, defaultColorStateList);
		}
		return defaultColorStateList;
	}

	public ColorStateList getSwitchTrackColorStateList(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		if (disabledColor == null)
		{
			disabledColor = getThemeAttrColor(android.R.attr.colorForeground);
		}
		if (normalColor == null)
		{
			normalColor = getThemeAttrColor(androidx.appcompat.R.attr.colorSwitchThumbNormal);
		}
		if (activatedColor == null)
		{
			activatedColor = getThemeAttrColor(android.R.attr.colorForeground);
		}
		Triplet<Integer, Integer, Integer> key = new Triplet<>(disabledColor, normalColor, activatedColor);
		ColorStateList switchTrackStateList = SWITCH_TRACK_STATE_LISTS.get(key);
		if (switchTrackStateList == null)
		{
			final int[][] states = new int[3][];
			final int[] colors = new int[3];
			int i = 0;

			// Disabled state
			states[i] = new int[]{-android.R.attr.state_enabled};
			colors[i] = getColorByApplyingAlpha(disabledColor, 0.1f);
			i++;

			states[i] = new int[]{android.R.attr.state_checked};
			colors[i] = getColorByApplyingAlpha(activatedColor, 0.3f);
			i++;

			// Default enabled state
			states[i] = new int[0];
			colors[i] = getColorByApplyingAlpha(normalColor, 0.3f);
			i++;

			switchTrackStateList = new ColorStateList(states, colors);
			SWITCH_TRACK_STATE_LISTS.put(key, switchTrackStateList);
		}
		return switchTrackStateList;
	}

	public ColorStateList getSwitchThumbColorStateList(Integer disabledColor, Integer normalColor, Integer activatedColor)
	{
		if (disabledColor == null)
		{
			disabledColor = getThemeAttrColor(androidx.appcompat.R.attr.colorSwitchThumbNormal);
		}
		if (normalColor == null)
		{
			normalColor = getThemeAttrColor(androidx.appcompat.R.attr.colorSwitchThumbNormal);
		}
		if (activatedColor == null)
		{
			activatedColor = getThemeAttrColor(androidx.appcompat.R.attr.colorControlActivated);
		}
		Triplet<Integer, Integer, Integer> key = new Triplet<>(disabledColor, normalColor, activatedColor);
		ColorStateList switchThumbStateList = SWITCH_THUMB_STATE_LISTS.get(key);
		if (switchThumbStateList == null)
		{
			final int[][] states = new int[3][];
			final int[] colors = new int[3];
			int i = 0;

			// Disabled state
			states[i] = new int[]{-android.R.attr.state_enabled};
			colors[i] = getColorByApplyingAlpha(disabledColor, disabledAlpha());
			i++;

			states[i] = new int[]{android.R.attr.state_checked};
			colors[i] = activatedColor;
			i++;

			// Default enabled state
			states[i] = new int[0];
			colors[i] = normalColor;

			i++;

			switchThumbStateList = new ColorStateList(states, colors);
			SWITCH_THUMB_STATE_LISTS.put(key, switchThumbStateList);
		}
		return switchThumbStateList;
	}

	public Drawable getProgressTrackDrawable(int progressTrackEmptyResourceId, int progressTrackFullResourceId)
	{
		return getProgressTrackDrawable(null, null, null, progressTrackEmptyResourceId, progressTrackFullResourceId);
	}

	public Drawable getProgressTrackDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int progressTrackEmptyResourceId, int progressTrackFullResourceId)
	{
		return getProgressTrackDrawable(disabledColor, normalColor, activatedColor, progressTrackEmptyResourceId, progressTrackFullResourceId, PorterDuff.Mode.SRC_IN,
				PorterDuff.Mode.SRC_IN);
	}

	public Drawable getProgressTrackDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int progressTrackEmptyResourceId, int progressTrackFullResourceId,
                                             PorterDuff.Mode emptyMode, PorterDuff.Mode fullMode)
	{
		if (disabledColor == null)
		{
			disabledColor = getDefaultDisabledColor();
		}
		if (normalColor == null)
		{
			normalColor = getDefaultNormalColor();
		}
		if (activatedColor == null)
		{
			activatedColor = getDefaultActivatedColor();
		}

		final int[][] states = new int[2][];
		states[0] = new int[]{-android.R.attr.state_enabled};
		states[1] = new int[0];

		Drawable empty = ContextCompat.getDrawable(context, progressTrackEmptyResourceId);
		if (empty != null)
		{
			final int[] colors = new int[2];
			// Disabled state
			colors[0] = getColorByApplyingAlpha(disabledColor, disabledAlpha());
			// Default enabled state
			colors[1] = normalColor;

			ColorStateList colorStateList = new ColorStateList(states, colors);
			empty = new TintDrawableWrapper(empty, colorStateList, emptyMode);
		}
		Drawable full = ContextCompat.getDrawable(context, progressTrackFullResourceId);
		if (full != null)
		{
			final int[] colors = new int[2];
			// Disabled state
			colors[0] = getColorByApplyingAlpha(activatedColor, disabledAlpha());
			// Default enabled state
			colors[1] = activatedColor;

			ColorStateList colorStateList = new ColorStateList(states, colors);
			full = new TintDrawableWrapper(full, colorStateList, fullMode);
			full = new ClipDrawable(full, Gravity.LEFT | Gravity.CENTER_VERTICAL, ClipDrawable.HORIZONTAL);
		}

		LayerDrawable drawable = null;
		if (empty != null && full != null)
		{
			drawable = new LayerDrawable(new Drawable[]{empty, full});
			drawable.setId(0, android.R.id.background);
			drawable.setId(1, android.R.id.progress);
		}

		return drawable;
	}

	public Drawable getProgressThumbDrawable(int progressThumbResourceId)
	{
		return getProgressThumbDrawable(null, null, null, progressThumbResourceId);
	}

	public Drawable getProgressThumbDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int progressThumbResourceId)
	{
		return getProgressThumbDrawable(disabledColor, normalColor, activatedColor, progressThumbResourceId, PorterDuff.Mode.SRC_IN);
	}

	public Drawable getProgressThumbDrawable(Integer disabledColor, Integer normalColor, Integer activatedColor, int progressThumbResourceId, PorterDuff.Mode thumbMode)
	{
		if (disabledColor == null)
		{
			disabledColor = getDefaultActivatedColor();
		}
		if (activatedColor == null)
		{
			activatedColor = getDefaultActivatedColor();
		}

		final int[][] states = new int[2][];
		states[0] = new int[]{-android.R.attr.state_enabled};
		states[1] = new int[0];

		Drawable thumb = ContextCompat.getDrawable(context, progressThumbResourceId);
		if (thumb != null)
		{
			final int[] colors = new int[2];
			// Disabled state
			colors[0] = getColorByApplyingAlpha(disabledColor, disabledAlpha());
			// Default enabled state
			colors[1] = activatedColor;

			ColorStateList colorStateList = new ColorStateList(states, colors);
			thumb = new TintDrawableWrapper(thumb, colorStateList, thumbMode);
		}

		return thumb;
	}
}
