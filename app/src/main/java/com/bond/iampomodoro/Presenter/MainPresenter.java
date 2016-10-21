package com.bond.iampomodoro.Presenter;

import android.content.Context;

import com.bond.iampomodoro.Model.SettingsHelper;
import com.bond.iampomodoro.Model.SettingsObject;

import javax.inject.Inject;

//TODO Make Singleton
//public class MainPresenter extends Service {
//public class MainPresenter implements Presenter {
public class MainPresenter {

    @Inject SettingsHelper settingsHelper;

    public void saveSetings(Context context, SettingsObject settings) {

        settingsHelper.setSetings(context, settings);
    }

    public SettingsObject notifySettingsFragmentStart(Context context) {

        return settingsHelper.getSetings(context);
    }
}
