package com.bond.iampomodoro.view.fragments;

import com.bond.iampomodoro.model.dto.UserSettingsObject;

import rx.Observable;

public interface SettingsView extends View {

    Observable showSettings(UserSettingsObject settings);
}
