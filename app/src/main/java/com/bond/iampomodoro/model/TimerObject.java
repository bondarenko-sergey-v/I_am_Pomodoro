package com.bond.iampomodoro.model;

public class TimerObject {
    public int minutes;
    public int seconds;
    public boolean isWorkTime;
    public int breaksCount;

    public TimerObject(int minutes, int seconds, boolean isWorkTime, int breaksCount) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.isWorkTime = isWorkTime;
        this.breaksCount = breaksCount;
    }
}