package com.bond.iampomodoro.model.dataObjects;

public class UserSettingsObject {
    public Boolean[] bool;
    public int workSession, breakMin, longBreak, sessionsBeforeLB;

    public UserSettingsObject(Boolean[] bool, int workSession, int breakMin,
                              int longBreak, int sessionsBeforeLB) {
        this.bool = bool;
        this.workSession = workSession;
        this.breakMin = breakMin;
        this.longBreak = longBreak;
        this.sessionsBeforeLB = sessionsBeforeLB;
    }
}