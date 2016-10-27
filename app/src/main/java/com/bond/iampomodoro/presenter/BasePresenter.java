package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
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

    //@Inject
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    TimerSettingsObject ob;

    public int workSessionMin;
    public int breakMin;
    public int longBreakMin;
    public int sessionsBeforeLB;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    abstract void resetTimer();
    abstract void startTimer();
    abstract void pauseTimer();

    abstract void showTime(int intervalInSeconds);

    public void getSettings() {

        SettingsObject settings = settingsHelper.getSetings();
        workSessionMin = settings.intr[0];
        breakMin = settings.intr[1];
        longBreakMin = settings.intr[2];
        sessionsBeforeLB = settings.intr[3];

        // Get timer setting
        ob = settingsHelper.getTimerSetings();

        setTimerState();
    }

    public void setTimerState() {

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
            return;
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
            countDownTimer(10);
            //countDownTimer(workSessionMin * 60);
            System.out.println("Work");
        } else if (ob.timerCycleCounter % 2 == 0
                && ob.timerCycleCounter % ((sessionsBeforeLB + 1) * 2) != 0) {
            countDownTimer(3);
            //countDownTimer(breakMin * 60);
            System.out.println("Break");
        } else {
            countDownTimer(7);
            //countDownTimer(longBreakMin * 60);
            System.out.println("Long break");
        }
    }

    private void countDownTimer(int intervalInSeconds) {
        clearCompositeSubscription();

        Subscription s =
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v +1)
                        .take(intervalInSeconds)
                        .doAfterTerminate(() -> timerStart())
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