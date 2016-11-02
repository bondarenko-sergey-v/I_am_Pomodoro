package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.view.fragments.SettingsView;

public class SettingsPresenter extends BasePresenter {

    private SettingsObject settings;

    public void onCreate(SettingsView view) {
        App.getAppComponent().inject(this);
        this.settings = model.getSettings();

        view.showSettings(settings);
    }

    public void onCheckBoxesChanges(Boolean[] checkBoxState) {
        settings.bool = checkBoxState;
    }

    public void onSeekbarsChanges(Integer[] seekbarState) {
        settings.intr = seekbarState;
    }

    public void onTabUnselected() {
        model.setSettings(settings);
    }
}