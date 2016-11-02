package com.bond.iampomodoro.model;

import com.bond.iampomodoro.App;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class ModelImpl {

    @Inject
    SettingsHelper settingsHelper;

    @Inject
    BehaviorSubject<Integer> behaviorSubject;

    @Inject
    CompositeSubscription compositeSubscription;

    private int workSessionMin;
    private int breakMin;
    private int longBreakMin;
    private int sessionsBeforeLB;

    private TimerSettingsObject ob;
    private SettingsObject settings;

    public ModelImpl() {
        App.getAppComponent().inject(this);
        this.ob = settingsHelper.getTimerSetings();

        getSettingsForModel();
    }

    public SettingsObject getSettings() {
        return settingsHelper.getSetings();
    }

    public void setSettings(SettingsObject settings) {
        settingsHelper.setSetings(settings);
    }

    public TimerSettingsObject getTimerState() {
        return settingsHelper.getTimerSetings();
    }

    private void setTimerState(TimerSettingsObject settings) {
        settingsHelper.setTimerSetings(settings);
    }

    public void getPicture() {
        //TODO Make it
    }

    public void startTimer() {
        clearCompositeSubscription();

        if(ob.timerState.equals("onPause")) {
            initBehaviorSubject(behaviorSubject.getValue());
            ob.timerState = "onStart";

            return;
        }

        ob.timerCycleCounter++;

        if (ob.timerCycleCounter % 2 != 0) {
            //initBehaviorSubject(15);  // Mock
            initBehaviorSubject(workSessionMin * 60);
        } else if (ob.timerCycleCounter % ((sessionsBeforeLB + 1) * 2) != 0) {
            //initBehaviorSubject(5);   // Mock
            initBehaviorSubject(breakMin * 60);
        } else {
            //initBehaviorSubject(10);  // Mock
            initBehaviorSubject(longBreakMin * 60);
        }

        ob.timerState = "onStart";
        setTimerState(ob);
    }

   public void pauseTimer() {
        clearCompositeSubscription();
        ob.timerState = "onPause";
        setTimerState(ob);
    }

    public void resetTimer() {
        clearCompositeSubscription();

        ob.timerCycleCounter = 0;
        ob.intervalInSeconds = workSessionMin * 60;
        ob.timerState = "onReset";
        setTimerState(ob);
    }

    private void getSettingsForModel() {

        settings = getSettings();
        ob = getTimerState();

        workSessionMin = settings.intr[0];
        breakMin = settings.intr[1];
        longBreakMin = settings.intr[2];
        sessionsBeforeLB = settings.intr[3];
    }

    private void initBehaviorSubject(int timeInSeconds) {

        Subscription s =
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v + 1)
                        .take(timeInSeconds)
                        .map(v -> (int) (timeInSeconds - v))
                        .doOnCompleted(this::startTimer)
                        .subscribe(behaviorSubject::onNext);

        compositeSubscription.add(s);
    }

    private void clearCompositeSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }
}