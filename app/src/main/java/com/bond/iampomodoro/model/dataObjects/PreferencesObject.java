package com.bond.iampomodoro.model.dataObjects;

public class PreferencesObject {
    public boolean daySound, dayVibration, dayKeepScreen, nightSound,
        nightVibration, nightKeepScreen, nightPictures;

    public int workSession, breakMin, longBreak, sessionsBeforeLB;

    public int timerCycleCounter, intervalInSeconds;
    public String timerState;

    public PreferencesObject(boolean daySound, boolean dayVibration, boolean dayKeepScreen,
                             boolean nightSound, boolean nightVibration, boolean nightKeepScreen,
                             boolean nightPictures, int workSession, int breakMin, int longBreak,
                             int sessionsBeforeLB, int timerCycleCounter, int intervalInSeconds,
                             String timerState) {
        this.daySound = daySound;
        this.dayVibration = dayVibration;
        this.dayKeepScreen = dayKeepScreen;
        this.nightSound = nightSound;
        this.nightVibration = nightVibration;
        this.nightKeepScreen = nightKeepScreen;
        this.nightPictures = nightPictures;

        this.workSession = workSession;
        this.breakMin = breakMin;
        this.longBreak = longBreak;
        this.sessionsBeforeLB = sessionsBeforeLB;

        this.timerCycleCounter = timerCycleCounter;
        this.intervalInSeconds = intervalInSeconds;
        this.timerState = timerState;
    }
}