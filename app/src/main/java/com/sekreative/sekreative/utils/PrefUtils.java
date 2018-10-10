package com.sekreative.sekreative.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public final class PrefUtils {

    private static SharedPreferences prefs;
    private static final String KEY_AUTH_TOKEN = "key-auth-token";

    public static void init(Application application) {
        prefs = PreferenceManager.getDefaultSharedPreferences(application);
    }

    public static void setAuthToken(String token) {
        prefs.edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply();
    }

    public static boolean isUserLoggedIn() {
        String authToken = prefs.getString(KEY_AUTH_TOKEN, "");
        return !TextUtils.isEmpty(authToken);
    }

}
