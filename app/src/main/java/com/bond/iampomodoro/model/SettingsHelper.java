package com.bond.iampomodoro.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bond.iampomodoro.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;

public class SettingsHelper {

    private static final String APP_PREF_OBJECT = "prefObject";

    @Inject
    Context appContext;

    public SettingsHelper() {
        App.getComponent().inject(this);
    }

    public SettingsObject getSetings() {
        SharedPreferences mSettings;

        mSettings = PreferenceManager.getDefaultSharedPreferences(appContext);

        String json = mSettings.getString(APP_PREF_OBJECT,
                "{\"bool\":[true,true,true,false,true,true,true],\"intr\":[25,5,15,4]}");

        return new Gson().fromJson(json,
                new TypeToken<SettingsObject>(){}.getType());
    }

    public void setSetings(SettingsObject settings) {

        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(appContext).edit();

        editor.putString(APP_PREF_OBJECT, new Gson().toJson(settings))
                .apply();
    }
}