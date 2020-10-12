package com.azumio.android.foodlenslibrary.utils;

import android.util.Log;

import androidx.annotation.Nullable;

import static java.lang.String.format;

public class PremiumStatus {
    private static final String LOG_TAG = PremiumStatus.class.getSimpleName();
    private static Boolean ASSUME_PREMIUM_WHEN_DONT_KNOW = false;
    public enum Status
    {
        INACTIVE("inactive"),
        ACTIVE("active");

        private final String status;

        Status(String status)
        {
            this.status = status;
        }


        public String getStatus()
        {
            return status;
        }
    }

    private Status mStatus;


    public Status getStatus()
    {
        return mStatus;
    }

    public  void  setStatus(Status status)
    {
        mStatus = status;
    }

    public boolean isPremium()
    {
        PremiumStatus.Status status = this.getStatus();
        if(status != null) {
            switch (status) {
                case ACTIVE:
                    return true;
                case INACTIVE:
                    return false;
                default:
                    String message = format("Unrecognized user status in the API - check the implementation, %s enum doesn't contain value \"%s\"", PremiumStatus.class.getSimpleName(),
                            status);
                    Log.e(LOG_TAG, message, new RuntimeException(message));
                    return ASSUME_PREMIUM_WHEN_DONT_KNOW;
            }
        }
        else return false;
    }

    public static boolean isPremium(@Nullable PremiumStatus data)
    {
        return data == null ? ASSUME_PREMIUM_WHEN_DONT_KNOW : data.isPremium();
    }
}
