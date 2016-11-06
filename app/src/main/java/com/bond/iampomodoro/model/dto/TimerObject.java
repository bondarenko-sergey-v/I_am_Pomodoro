package com.bond.iampomodoro.model.dto;

public class TimerObject {
    public int intervalInSeconds;
    public String timerState;

    public TimerObject(int intervalInSeconds, String timerState) {
        this.intervalInSeconds = intervalInSeconds;
        this.timerState = timerState;
    }
}