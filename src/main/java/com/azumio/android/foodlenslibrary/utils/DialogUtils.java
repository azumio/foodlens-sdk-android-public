package com.azumio.android.foodlenslibrary.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.Nullable;

import com.azumio.android.foodlenslibrary.R;


public class DialogUtils
{
	public ProgressDialog mProgressDialog;
	private Context context;
	private static final Runnable noAction = () ->
	{
	};

	public DialogUtils(Context context) {
		this.context = context;
	}

	public void showAlertDialog(String title, String message, Context context)
	{
		showAlertDialog(title, message, context, noAction);
	}

	public void showLoadingDialog(Context context)
	{
		showDialog(context, R.string.foodlens_loading);
	}


	public void showDialog(Context context, int stringMessage)
	{
		if (mProgressDialog == null)
		{
			mProgressDialog = new ProgressDialog(context);
		}
		mProgressDialog.setMessage(context.getString(stringMessage));
		mProgressDialog.show();
		mProgressDialog.setCancelable(false);
	}

	public void clearDialog()
	{
		if (mProgressDialog != null)
		{
			if (mProgressDialog.isShowing())
			{
				mProgressDialog.dismiss();
				mProgressDialog = null;
			}
		}
	}

	public void showAlertDialog(String message, Context context)
	{
		showAlertDialog("", message, context);
	}

	public void showAlertDialog(String message, Context context, Runnable negativeAction) {
		showAlertDialog("", message, context, negativeAction);
	}

	public void showAlertDialog(String title, String message, Context context, Runnable negativeAction)
	{
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		applyTitleIfNotEmpty(builder, title);
		builder.setMessage(message)
				.setNegativeButton(R.string.foodlens_action_ok, (dialog, id) ->
				{
					negativeAction.run();
					dialog.cancel();
				}).create()
				.show();
	}

	public Dialog showAlertDialog(String title, String message, Context context, Runnable positiveAction, String ok, String cancel)
	{
		return showAlertDialog(title, message, context, ok, positiveAction, cancel, () -> {});
	}

	public Dialog showAlertDialog(String message, Context context, String positiveButtonText, Runnable positiveAction, String negativeButtonText)
	{
		return showAlertDialog("", message, context, positiveButtonText, positiveAction, negativeButtonText, noAction);
	}

	public Dialog showAlertDialog(String message, Context context, String positiveButtonText, String negativeButtonText)
	{
		return showAlertDialog("", message, context, positiveButtonText, () -> {}, negativeButtonText, noAction);
	}

	public Dialog showAlertDialog(String message, Context context, String positiveButtonText, Runnable positiveAction, String negativeButtonText, Runnable negativeAction)
	{
		return showAlertDialog("", message, context, positiveButtonText, positiveAction, negativeButtonText, negativeAction);
	}

	public Dialog showAlertDialog(String title, String message, Context context, String positiveButtonText, Runnable positiveAction, String negativeButtonText, Runnable negativeAction)
	{
		//TODO merge with main method
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		applyTitleIfNotEmpty(builder, title);
		Dialog alertDialog = builder.setMessage(message)
				.setPositiveButton(positiveButtonText, (dialog, id) ->
				{
					positiveAction.run();
					dialog.cancel();
				})
				.setNegativeButton(negativeButtonText, (dialog, which) ->
				{
					if (negativeAction != null)
					{
						negativeAction.run();
					}
					dialog.cancel();
				})
				.create();
		alertDialog.show();
		return alertDialog;
	}

	public Dialog showAlertDialog(String title,
                                  String message,
                                  Context context,
                                  String positiveButtonText,
                                  Runnable positiveAction,
                                  String negativeButtonText,
                                  Runnable negativeAction,
                                  Runnable onDismiss)
	{
		//TODO merge with main method
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		applyTitleIfNotEmpty(builder, title);
		Dialog alertDialog = builder.setMessage(message)
				.setPositiveButton(positiveButtonText, (dialog, id) ->
				{
					positiveAction.run();
					dialog.cancel();
				})
				.setNegativeButton(negativeButtonText, (dialog, which) ->
				{
					if (negativeAction != null)
					{
						negativeAction.run();
					}
					dialog.cancel();
				})
				.setOnDismissListener((dialogInterface) -> {
					onDismiss.run();
				})
				.create();
		alertDialog.show();
		return alertDialog;
	}

	private void applyTitleIfNotEmpty(AlertDialog.Builder builder, String title) {
		if (!isEmptyOrWhitespace(title)) {
			builder.setTitle(title);
		}
	}

	private  boolean isEmptyOrWhitespace(@Nullable String var0) {
		return var0 == null || var0.trim().isEmpty();
	}
}
