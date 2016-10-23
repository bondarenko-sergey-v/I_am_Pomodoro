package com.bond.iampomodoro.model;

import android.content.Context;

public interface SettingsMvpHelper {

    SettingsObject getSetings(Context context);

    void setSetings(Context context, SettingsObject settings);
    //TODO Delete Context link

}
