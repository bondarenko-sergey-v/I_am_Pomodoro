package com.bond.iampomodoro.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

public class SettingsHelper {

    private static final String APP_PREF_OBJECT = "prefObject";

    //@Inject SharedPreferences sharedPreferences;

    public SettingsObject getSetings(Context context) {
        SharedPreferences mSettings;

        mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        //String json = sharedPreferences.getString(APP_PREF_OBJECT,
        String json = mSettings.getString(APP_PREF_OBJECT,
                "{\"bool\":[true,true,true,false,true,true,true],\"intr\":[25,5,15,4]}");

        return new Gson().fromJson(json,
                new TypeToken<SettingsObject>(){}.getType());
    }

    public void setSetings(Context context, SettingsObject settings) {

        //SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context).edit();

        editor.putString(APP_PREF_OBJECT, new Gson().toJson(settings))
                .apply();
    }
}