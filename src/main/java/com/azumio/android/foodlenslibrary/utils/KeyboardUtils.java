package com.azumio.android.foodlenslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public class KeyboardUtils
{
	public static void showSoftKeyboard(@NonNull View view)
	{
		if (view == null)
		{
			return;
		}

		InputMethodManager inputMethodManager = getInputMethodManager(view);
		inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	public static void hideSoftKeyboard(@NonNull View view)
	{
		if (view == null)
		{
			return;
		}

		InputMethodManager inputMethodManager = getInputMethodManager(view);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void toggleSoftKeyboard(@NonNull View view)
	{
		if (view == null)
		{
			return;
		}

		InputMethodManager inputMethodManager = getInputMethodManager(view);
		inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	private static InputMethodManager getInputMethodManager(@NonNull View view)
	{
		return (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public  static void hideKeyboard(Activity context) {
		InputMethodManager inputManager = (InputMethodManager)ApplicationContextProvider.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

		// check if no view has focus:
		View v = context.getCurrentFocus();
		if (v == null)
			return;

		inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	public static void toggle(Activity activity){
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		if (imm.isActive()){
			imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
		}
		// else {
		//			imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY); // show
		//		}
	}

	public static void hideForcefully(Activity activity)
	{
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(
				Activity.INPUT_METHOD_SERVICE);
		if (imm.isActive())
		{
			imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
		}
	}
}
