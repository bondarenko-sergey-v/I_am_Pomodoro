package com.bond.iampomodoro.presenter;

import android.content.Context;

import com.bond.iampomodoro.model.SettingsObject;

public interface PresenterInterface {

    void saveSetings(Context context, SettingsObject settings);

    SettingsObject notifySettingsFragmentStart(Context context);

}
