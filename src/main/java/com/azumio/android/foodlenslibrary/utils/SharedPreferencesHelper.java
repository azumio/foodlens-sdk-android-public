package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.azumio.android.foodlenslibrary.FoodLens;
import com.azumio.android.foodlenslibrary.core.AccessTokenType;
import com.azumio.android.foodlenslibrary.core.ArgusToken;

public class SharedPreferencesHelper {
    private static final String SHARED_PREFERENCES_HELPER = "sharedPreferencesHelper";
    private static final String LAST_ACCESS_TOKEN = "lastAccessToken";
    private static final String LAST_ACCESS_TOKEN_TYPE = "lastAccessTokenType";


    private static SharedPreferences getPreferences() {
        return ApplicationContextProvider.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_HELPER, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPreferencesEditor() {
        return ApplicationContextProvider.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_HELPER, Context.MODE_PRIVATE).edit();
    }


    public static ArgusToken lastFoodLensAccessToken() {
        String s = getPreferences().getString(LAST_ACCESS_TOKEN, null);
        if (s != null)
            return ArgusToken.Companion.token(s);
        return null;
    }

    public static void setlastFoodLensAccessToken(ArgusToken token) {
        getPreferencesEditor().putString(LAST_ACCESS_TOKEN, token.getStringRepresentation()).commit();
    }

    public static void setlastFoodLensAccessTokenType(AccessTokenType tokenType) {
        getPreferencesEditor().putInt(LAST_ACCESS_TOKEN_TYPE, tokenType.ordinal()).commit();
    }

    public static AccessTokenType lastFoodLensAccessTokenType() {
        int t = getPreferences().getInt(LAST_ACCESS_TOKEN_TYPE, -1);
        switch (t) {
            case 0:
                return AccessTokenType.Bearer;
            case 1:
                return AccessTokenType.BearerOAuth1_0;
            default:
                return null;

        }
    }

}
