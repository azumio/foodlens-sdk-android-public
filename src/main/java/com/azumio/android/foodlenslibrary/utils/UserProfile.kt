package com.azumio.android.foodlenslibrary.utils

class UserProfile {
    companion object
    {
        fun getPremiumStatus():PremiumStatus
        {
            val status = PremiumStatus()
            status.status = PremiumStatus.Status.ACTIVE
            return  status
        }
    }
}