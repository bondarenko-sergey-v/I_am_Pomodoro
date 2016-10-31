package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.util.NotifyUser;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.model.TimerSettingsObject;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    @Inject
    SettingsHelper settingsHelper;
    @Inject
    NotifyUser notifyUser;

    //@Inject
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    TimerSettingsObject ob;
    SettingsObject generalSettings;

    public int workSessionMin;
    public int breakMin;
    public int longBreakMin;
    public int sessionsBeforeLB;

    public BasePresenter() {
        App.getAppComponent().inject(this);
    }

    abstract void startTimer();
    abstract void pauseTimer();
    abstract void resetTimer();
    abstract void showTime(int intervalInSeconds);
    abstract void notifyUser(NotifyUser notifyUser);

    public void getSettingsAndRestoreTimer() {

        generalSettings = settingsHelper.getSetings();
        workSessionMin = generalSettings.intr[0];
        breakMin = generalSettings.intr[1];
        longBreakMin = generalSettings.intr[2];
        sessionsBeforeLB = generalSettings.intr[3];

        // Get timer setting
        ob = settingsHelper.getTimerSetings();

        if(ob.intervalInSeconds == 0) { ob.intervalInSeconds = 1; }

        // Restore timer state
        if(ob.timerCycleCounter == 0) {
            resetTimer();
            return;
        }

        if(!ob.isTimerOnPause && ob.timerCycleCounter != 0) {
            ob.isTimerOnPause = true;
            startTimer();
            return;
        }

        if(ob.isTimerOnPause) {
            pauseTimer();
        }
    }

    public void saveTimerSettings() {
        clearCompositeSubscription();

        settingsHelper.setTimerSetings(ob);
    }

    public void timerStart() {

        if(ob.isTimerOnPause) {
            countDownTimer(ob.intervalInSeconds);
            ob.isTimerOnPause = false;
            return;
        }

        ob.timerCycleCounter++;

        if (ob.timerCycleCounter % 2 != 0) {
            //countDownTimer(15);
            countDownTimer(workSessionMin * 60);
        } else if (ob.timerCycleCounter % ((sessionsBeforeLB + 1) * 2) != 0) {
            //countDownTimer(5);
            countDownTimer(breakMin * 60);
        } else {
            //countDownTimer(10);
            countDownTimer(longBreakMin * 60);
        }
    }

    private void countDownTimer(int intervalInSeconds) {
        clearCompositeSubscription();

        Subscription s =
            Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(v -> v + 1)
                .take(intervalInSeconds)
                .doAfterTerminate(() -> {timerStart(); notifyUser(notifyUser);})
                .subscribe(v -> {
                    ob.intervalInSeconds = (int) (intervalInSeconds - v);
                    showTime(ob.intervalInSeconds);
                });

        compositeSubscription.add(s);
    }

    public void clearCompositeSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
            compositeSubscription = new CompositeSubscription();
        }
    }
}