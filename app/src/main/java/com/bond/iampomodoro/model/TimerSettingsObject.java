package com.bond.iampomodoro.model;

public class TimerSettingsObject {
    public int savedMinutes;
    public int savedSeconds;
    public boolean isWorkTime;
    public int breaksCount;
    public boolean isCompSubscriptionHasSubscriptions;

    public TimerSettingsObject(int savedMinutes, int savedSeconds, boolean isWorkTime,
                               int breaksCount, boolean isCompSubscriptionHasSubscriptions) {
        this.savedMinutes = savedMinutes;
        this.savedSeconds = savedSeconds;
        this.isWorkTime = isWorkTime;
        this.breaksCount = breaksCount;
        this.isCompSubscriptionHasSubscriptions = isCompSubscriptionHasSubscriptions;
    }
}