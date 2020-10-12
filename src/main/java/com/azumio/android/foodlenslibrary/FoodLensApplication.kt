package com.azumio.android.foodlenslibrary

import android.Manifest
import android.R
import android.app.Application
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat
import com.azumio.android.foodlenslibrary.utils.reachability.InternetReachabilityManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import java.util.*



class FoodLensApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Logger.addLogAdapter(DiskLogAdapter())
            }

        setupInternetReachablityManager()
        Logger.i("Application started")
    }

    private val mInternetConnectivityObserver = Observer { observable, data -> }

    private fun setupInternetReachablityManager() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(InternetReachabilityManager(), filter)

        InternetReachabilityManager.addObserver(mInternetConnectivityObserver)

    }
}