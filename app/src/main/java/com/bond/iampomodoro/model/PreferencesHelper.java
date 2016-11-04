package com.bond.iampomodoro.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bond.iampomodoro.model.dataObjects.PreferencesObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PreferencesHelper {

    private static final String APP_PREF_GENERAL = "general";
    private final Context context;

    public PreferencesHelper(Context context) {
        this.context = context;
    }

    public PreferencesObject getPreferences() {

        String json = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(APP_PREF_GENERAL,
                "{\"breakMin\":5,\"dayKeepScreen\":true,\"daySound\":true,\"dayVibration\":true," +
                "\"intervalInSeconds\":1,\"longBreak\":15,\"nightKeepScreen\":true," +
                "\"nightPictures\":true,\"nightSound\":false,\"nightVibration\":true," +
                "\"sessionsBeforeLB\":4,\"timerCycleCounter\":0,\"timerState\":\"onReset\"," +
                "\"workSession\":25}");

        return new Gson().fromJson(json,
                new TypeToken<PreferencesObject>(){}.getType());
    }

    public void setPreferences(PreferencesObject pref) {

        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();

        editor.putString(APP_PREF_GENERAL, new Gson().toJson(pref))
                .apply();
    }
}