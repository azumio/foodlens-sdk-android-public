package com.azumio.android.foodlenslibrary.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;

public class ColorUtils
{
	public static final float STATUS_DEFAULT_COLOR_RATIO = 0.2f;

	public static int blendColors(@ColorInt int color1, @ColorInt int color2, float ratio)
	{
		final float inverseRation = 1f - ratio;
		float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
		float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
		float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
		return Color.rgb((int) r, (int) g, (int) b);
	}

	public static boolean isBrightColor(@ColorInt int color)
	{
		// is transparent
		if (Color.alpha(color) == 0)
		{
			return true;
		}
		int[] rgb = {Color.red(color), Color.green(color), Color.blue(color)};

		int brightness = (int) Math.sqrt(rgb[0] * rgb[0] * .241 + rgb[1] * rgb[1] * .691 + rgb[2] * rgb[2] * .068);

		return brightness >= 200;
	}

	public static int darkerColor(@ColorInt int color, float darkeningRate)
	{
		darkeningRate = 1.f - Math.min(Math.max(darkeningRate, 0.f), 1.f);
		int newRed = (int) (Color.red(color) * darkeningRate);
		int newGreen = (int) (Color.green(color) * darkeningRate);
		int newBlue = (int) (Color.blue(color) * darkeningRate);
		return Color.argb(Color.alpha(color), newRed, newGreen, newBlue);
	}

	public static int lighterColor(@ColorInt int color, float lighteningRate)
	{
		lighteningRate = Math.min(Math.max(lighteningRate, 0.f), 1.f);
		int newRed = Math.min(Color.red(color) + (int) ((255 - Color.red(color)) * lighteningRate), 255);
		int newGreen = Math.min(Color.green(color) + (int) ((255 - Color.green(color)) * lighteningRate), 255);
		int newBlue = Math.min(Color.blue(color) + (int) ((255 - Color.blue(color)) * lighteningRate), 255);
		return Color.argb(Color.alpha(color), newRed, newGreen, newBlue);
	}

	public static void setStatusBarColor(Activity activity, @ColorInt int statusBarColor, @ColorInt int toolbarColor)
	{
		if (activity == null || activity.getWindow() == null)
		{
			return;
		}
		int correctColor = statusBarColor;
		if (isBrightColor(toolbarColor))
		{
			correctColor = Color.BLACK;
		}

		activity.getWindow().setStatusBarColor(correctColor);
	}

	public static void setStatusBarColor(Activity activity, @ColorInt int statusBarColor)
	{
		if (activity == null || activity.getWindow() == null)
		{
			return;
		}
		activity.getWindow().setStatusBarColor(statusBarColor);
	}

	public static ColorFilter getColorFilterFromColor(@ColorInt int color)
	{
		return new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY);
	}

	public static void setToolbarTextAndIconColors(ViewGroup viewGroup, @ColorInt int color)
	{
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			View child = viewGroup.getChildAt(i);

			if (child instanceof ViewGroup)
			{
				setToolbarTextAndIconColors((ViewGroup) child, color);
			}
			else if (child instanceof ImageView)
			{
				ImageView imageView = (ImageView) child;
				imageView.setColorFilter(getColorFilterFromColor(color));
			}
			else if (child instanceof TextView)
			{
				TextView textView = (TextView) child;
				textView.setTextColor(color);
			}
		}
	}
}
