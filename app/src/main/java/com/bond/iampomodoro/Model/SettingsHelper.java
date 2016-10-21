package com.bond.iampomodoro.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
//public class SettingsHelper implements SettingsMvpHelper {
public class SettingsHelper {

    //TODO Make Singleton
    private static final String APP_PREF = "mysettings";
    private static final String APP_PREF_OBJECT = "prefObject";

    @Inject SharedPreferences sharedPreferences;
    //@Override
    @Inject
    public SettingsObject getSetings(@ApplicationContext Context context) {
        SharedPreferences mSettings;

        //mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        //mSettings = PreferenceManager.getDefaultSharedPreferences(Context);
        String json = sharedPreferences.getString(APP_PREF_OBJECT,
                "{\"bool\":[true,true,true,false,true,true,true],\"intr\":[25,5,15,4]}");

        return new Gson().fromJson(json,
                new TypeToken<SettingsObject>(){}.getType());
    }

    //@Override
    public void setSetings(@ApplicationContext Context context, SettingsObject settings) {
        //SharedPreferences.Editor editor;
        //editor = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit();

        SharedPreferences.Editor editor;
        editor = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit();

        String json = new Gson().toJson(settings);

        editor.putString(APP_PREF_OBJECT, json).apply();
    }
}