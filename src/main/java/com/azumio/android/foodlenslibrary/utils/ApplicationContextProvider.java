package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;

public final class ApplicationContextProvider
{
    private static volatile Context mApplicationContext;

    public static synchronized void setApplicationContext(Context context)
    {
        if (context != null)
        {
            mApplicationContext = context.getApplicationContext();
        }
    }

    public static synchronized Context getApplicationContext()
    {
        return mApplicationContext;
    }
}