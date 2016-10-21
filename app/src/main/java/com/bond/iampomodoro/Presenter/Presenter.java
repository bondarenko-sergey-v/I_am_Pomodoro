package com.bond.iampomodoro.Presenter;

import android.content.Context;

import com.bond.iampomodoro.Model.SettingsObject;

public interface Presenter {

    void saveSetings(Context context, SettingsObject settings);

    SettingsObject notifySettingsFragmentStart(Context context);

}
