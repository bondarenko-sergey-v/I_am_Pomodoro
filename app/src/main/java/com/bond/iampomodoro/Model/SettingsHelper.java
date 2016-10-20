package com.bond.iampomodoro.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SettingsHelper {

    private static final String APP_PREF = "mysettings";
    private static final String APP_PREF_OBJECT = "prefObject";

    public static SettingsObject getSettings(Context context) {
        SharedPreferences mSettings;
        String initialSettings = "{\"bool\":[true,true,true,false,true,true,true]," +
                "\"intr\":[25,5,15,4]}";

        mSettings = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        String json = mSettings.getString(APP_PREF_OBJECT, initialSettings);

        return new Gson().fromJson(json,
                new TypeToken<SettingsObject>(){}.getType());
    }

    public static void setSettings(Context context, SettingsObject settings) {
        SharedPreferences.Editor editor;
        editor = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit();

        String json = new Gson().toJson(settings);

        editor.putString(APP_PREF_OBJECT, json).apply();
    }

    public static class SettingsObject {
        public Boolean[] bool;
        public Integer[] intr;

        public SettingsObject(Boolean[] bool, Integer[] intr) {
            this.bool = bool;
            this.intr = intr;
        }
    }
}