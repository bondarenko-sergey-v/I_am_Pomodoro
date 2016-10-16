package com.bond.iampomodoro.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsHelper {

    private static final String APP_PREF = "mysettings";

    private static final String APP_PREF_WORK_SESSION = "WorkSession";
    private static final String APP_PREF_BREAK = "Break";
    private static final String APP_PREF_LONG_BREAK = "LongBreak";
    private static final String APP_PREF_SESSION_BEFORE_L_B = "SessionBeforeLB";

    private static final String APP_PREF_DAY_SOUND = "DaySound";
    private static final String APP_PREF_DAY_VIBRATION = "DayVibration";
    private static final String APP_PREF_DAY_KEEP_SCREEN = "DayKeepScreen";

    private static final String APP_PREF_NIGHT_SOUND = "NightSound";
    private static final String APP_PREF_NIGHT_VIBRATION = "NightVibration";
    private static final String APP_PREF_NIGHT_KEEP_SCREEN = "NightKeepScreen";
    private static final String APP_PREF_NIGHT_PICTURES = "NightPictures";

    public static int[] getSettings(Context context) {
        int [] settings = new int[11];
        SharedPreferences mSettings;

        mSettings = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);

        settings[0] = mSettings.getInt(APP_PREF_WORK_SESSION, 25);
        settings[1] = mSettings.getInt(APP_PREF_BREAK, 5);
        settings[2] = mSettings.getInt(APP_PREF_LONG_BREAK, 15);
        settings[3] = mSettings.getInt(APP_PREF_SESSION_BEFORE_L_B, 4);

        settings[4] = mSettings.getInt(APP_PREF_DAY_SOUND, 1);
        settings[5] = mSettings.getInt(APP_PREF_DAY_VIBRATION, 1);
        settings[6] = mSettings.getInt(APP_PREF_DAY_KEEP_SCREEN, 1);

        settings[7] = mSettings.getInt(APP_PREF_NIGHT_SOUND, 0);
        settings[8] = mSettings.getInt(APP_PREF_NIGHT_VIBRATION, 1);
        settings[9] = mSettings.getInt(APP_PREF_NIGHT_KEEP_SCREEN, 1);
        settings[10] = mSettings.getInt(APP_PREF_NIGHT_PICTURES, 1);

        return settings;
    }

    public static void setSettings(Context context, int[] settings) {
        int [] settingsArr = settings;
        SharedPreferences mSettings;
        SharedPreferences.Editor editor;

        mSettings = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        editor.putInt(APP_PREF_WORK_SESSION, settingsArr[0])
            .putInt(APP_PREF_BREAK, settingsArr[1])
            .putInt(APP_PREF_LONG_BREAK, settingsArr[2])
            .putInt(APP_PREF_SESSION_BEFORE_L_B, settingsArr[3])

            .putInt(APP_PREF_DAY_SOUND, settingsArr[4])
            .putInt(APP_PREF_DAY_VIBRATION, settingsArr[5])
            .putInt(APP_PREF_DAY_KEEP_SCREEN, settingsArr[6])

            .putInt(APP_PREF_NIGHT_SOUND, settingsArr[7])
            .putInt(APP_PREF_NIGHT_VIBRATION, settingsArr[8])
            .putInt(APP_PREF_NIGHT_KEEP_SCREEN, settingsArr[9])
            .putInt(APP_PREF_NIGHT_PICTURES, settingsArr[10])

            .apply();
    }
}
