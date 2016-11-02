package com.bond.iampomodoro.model;

public class TimerSettingsObject {
    public int timerCycleCounter;
    public int intervalInSeconds;
    public String timerState;

    public TimerSettingsObject(int timerCycleCounter, int intervalInSeconds,
                               String timerState) {
        this.timerCycleCounter = timerCycleCounter;
        this.intervalInSeconds = intervalInSeconds;
        this.timerState = timerState;
    }
}