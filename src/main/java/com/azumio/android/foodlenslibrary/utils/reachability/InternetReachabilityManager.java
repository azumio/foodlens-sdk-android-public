package com.azumio.android.foodlenslibrary.utils.reachability;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.azumio.android.foodlenslibrary.utils.ApplicationContextProvider;

import java.util.Observable;
import java.util.Observer;

public class InternetReachabilityManager extends BroadcastReceiver
{
	private static final String LOG_TAG = InternetReachabilityManager.class.getSimpleName();
	private static final InternetStateObservable INTERNET_STATE_OBSERVABLE = new InternetStateObservable();
	private static volatile boolean isOnline = false;

	public static void addObserver(Observer observer)
	{
		INTERNET_STATE_OBSERVABLE.addObserver(observer);
	}

	public static void deleteObserver(Observer observer)
	{
		INTERNET_STATE_OBSERVABLE.deleteObserver(observer);
	}

	public static boolean isOnline()
	{
		synchronized (InternetReachabilityManager.class)
		{
			return isOnline;
		}
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (context == null)
		{
			Log.w(LOG_TAG, "onReceive context is null!");
			context = ApplicationContextProvider.getApplicationContext();
		}

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		boolean connected = activeNetInfo != null && activeNetInfo.isConnected();

		boolean changed;
		synchronized (InternetReachabilityManager.class)
		{
			changed = isOnline != connected;
			isOnline = connected;
		}
		if (changed)
		{
			Log.i(LOG_TAG, "Network is " + (connected ? "connected. Assuming the internet is reachable..." : "not connected"));
			INTERNET_STATE_OBSERVABLE.setInternetAvailability(connected);
		}
	}

	private static class InternetStateObservable extends Observable
	{
		public void setInternetAvailability(boolean value)
		{
			setChanged();
			notifyObservers(value);
		}
	}
}
