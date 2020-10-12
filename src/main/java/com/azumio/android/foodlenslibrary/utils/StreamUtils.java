package com.azumio.android.foodlenslibrary.utils;

import android.util.Log;

import java.io.InputStream;

public class StreamUtils
{
	private static final String LOG_TAG = StreamUtils.class.getName();

	public static void quietCloseStream(InputStream inputStream)
	{
		if (inputStream != null)
		{
			try
			{
				inputStream.close();
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, String.valueOf(t));
			}
		}
	}
}
