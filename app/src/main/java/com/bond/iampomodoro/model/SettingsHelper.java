package com.bond.iampomodoro.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bond.iampomodoro.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;

public class SettingsHelper {

    private static final String APP_PREF_GENERAL = "GENERAL";
    private static final String APP_PREF_TIMER = "TIMER";

    private SharedPreferences mSettings;
    private SharedPreferences.Editor editor;

    @Inject
    Context appContext;

    public SettingsHelper() {
        App.getComponent().inject(this);
    }

    public SettingsObject getSetings() {

        mSettings = PreferenceManager.getDefaultSharedPreferences(appContext);

        String json = mSettings.getString(APP_PREF_GENERAL,
                "{\"bool\":[true,true,true,false,true,true,true],\"intr\":[25,5,15,4]}");

        return new Gson().fromJson(json,
                new TypeToken<SettingsObject>(){}.getType());
    }

    public void setSetings(SettingsObject settings) {

        editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();

        editor.putString(APP_PREF_GENERAL, new Gson().toJson(settings))
                .apply();
    }

    public TimerSettingsObject getTimerSetings() {

        mSettings = PreferenceManager.getDefaultSharedPreferences(appContext);

        String json = mSettings.getString(APP_PREF_TIMER,
                "{\"breaksCount\":0,\"isCompSubscriptionHasSubscriptions\":false," +
                "\"isWorkTime\":true,\"savedMinutes\":0,\"savedSeconds\":0}");

        return new Gson().fromJson(json,
                new TypeToken<TimerSettingsObject>(){}.getType());
    }

    public void setTimerSetings(TimerSettingsObject timerSetings) {

        editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();

        editor.putString(APP_PREF_TIMER, new Gson().toJson(timerSetings))
                .apply();
        System.out.println(new Gson().toJson(timerSetings));
    }
}