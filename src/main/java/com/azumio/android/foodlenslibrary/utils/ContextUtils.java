package com.azumio.android.foodlenslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

/**
 * Helper methods to assert lifecycle state of Activities and Fragments
 */
public class ContextUtils
{
	/**
	 * If so, it's not safe to eg. show a dialog box on the activity
	 *
	 * @see ContextUtils#isNotFinishing(Activity)
	 */
	public static boolean isGoneOrFinishing(@Nullable Activity activity)
	{
		return activity == null || activity.isFinishing();
	}

	/**
	 * It's safe to eg. show a dialog box in the Activity in such case
	 *
	 * @see ContextUtils#isGoneOrFinishing(Activity)
	 */
	public static boolean isNotFinishing(@Nullable Activity activity)
	{
		return !isGoneOrFinishing(activity);
	}

	/**
	 * Fragment is attached to an Activity, and the Activity isn't gone or finishing
	 *
	 * @see ContextUtils#isDetachedOrAttachedToFinishing(Fragment)
	 */
	public static boolean isAttachedToNotFinishing(@NonNull Fragment fragment)
	{
		return !isDetachedOrAttachedToFinishing(fragment);
	}

	/**
	 * Fragment is detached from an Activity, Activity can't be retrieved or is finishing already
	 *
	 * @see ContextUtils#isAttachedToNotFinishing(Fragment)
	 */
	public static boolean isDetachedOrAttachedToFinishing(@NonNull Fragment fragment)
	{
		return fragment.isDetached() || isGoneOrFinishing(fragment.getActivity());
	}

	public static boolean isOutOfSight(Fragment fragment)
	{
		return !fragment.isVisible() || !fragment.isAdded() || !fragment.getUserVisibleHint();
	}

	public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle options)
	{
		if (context instanceof Activity)
		{
			ActivityCompat.startActivity((Activity) context, intent, options);
		}
		else
		{
			context.startActivity(intent);
		}
	}

	public static void startActivity(Context context, Intent intent)
	{
		startActivity(context, intent, null);
	}

	public static void finishIfActivity(Context context)
	{
		if (context instanceof Activity)
		{
			Activity activity = (Activity) context;
			activity.finish();
		}
	}
}
