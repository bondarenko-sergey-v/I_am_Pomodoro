package com.bond.iampomodoro.model;

public class TimerSettingsObject {
    public int timerCycleCounter;
    public int intervalInSeconds;
    public boolean isTimerOnPause;

    public TimerSettingsObject(int timerCycleCounter, int intervalInSeconds,
                               boolean isTimerOnPause) {
        this.timerCycleCounter = timerCycleCounter;
        this.intervalInSeconds = intervalInSeconds;
        this.isTimerOnPause = isTimerOnPause;
    }
}