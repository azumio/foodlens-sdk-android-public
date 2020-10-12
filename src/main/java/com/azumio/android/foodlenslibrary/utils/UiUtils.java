package com.azumio.android.foodlenslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;

import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;





public class UiUtils
{
	public static void changeDrawableBackground(ViewGroup mealPlanTest, Context context, int color)
	{
		Drawable frameBackground = mealPlanTest.getBackground();
		if (frameBackground instanceof GradientDrawable)
		{
			((GradientDrawable) frameBackground).setColor(ContextCompat.getColor(context, color));
		}
	}

	public static float px(Context context, float dp)
	{
		if (context == null)
		{
			context = ApplicationContextProvider.getApplicationContext();
		}
		Resources resources = context.getResources();
		DisplayMetrics displayMetrics = resources.getDisplayMetrics();

		float density = displayMetrics.density;
		return (int) Math.ceil(dp * density);
	}



	public static void setupFullscreen(Activity activity)
	{
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}


