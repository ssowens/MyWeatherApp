package com.ssowens.android.myweatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

/**
 * Created by Sheila Owens on 2/28/19.
 */
public class LocationPreferences {
    public static final String LAT_KEY = "mylat";
    public static final String LONG_KEY = "mylong";
    private static final String PREF_LAST_RESULT_ID = "lastResultId";

    public String getLastResultId(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_LAST_RESULT_ID, null);
    }

    public void setPrefLastResultId(Context context, String lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }

}
