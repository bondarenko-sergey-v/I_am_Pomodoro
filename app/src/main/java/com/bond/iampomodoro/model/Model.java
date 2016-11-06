package com.bond.iampomodoro.model;

import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;

public interface Model {

    void startTimer();

    void pauseTimer();

    void resetTimer();

    UserSettingsObject getUserSettings();

    void setUserSettings(UserSettingsObject usrSet);
}
