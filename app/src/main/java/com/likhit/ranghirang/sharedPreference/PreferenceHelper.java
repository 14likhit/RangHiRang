package com.likhit.ranghirang.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class PreferenceHelper {

    private static final String PREF_APP_NAME = "app_pref_name_rang_hi_rang";
    private static final String KEY_IS_USER_LOGGED_IN = "USER";

    private static PreferenceHelper mInstance;
    private SharedPreferences sharedPreferences;

    public static void initialize(Context context) {
        if (mInstance == null) {
            mInstance = new PreferenceHelper(context);
        }
    }

    private PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_APP_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Call initialize() once before using the Shared Preferences");
        }
        return mInstance;
    }

    public void saveUserLoggedIn(Boolean user) {
        sharedPreferences.edit().putBoolean(KEY_IS_USER_LOGGED_IN, user).apply();
    }

    @Nullable
    public boolean getUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_USER_LOGGED_IN, false);
    }


}
