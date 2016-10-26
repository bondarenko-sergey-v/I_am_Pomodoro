package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;

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

    public boolean isWorkTime = true;
    public int breaksCount = 0;

    public int workSessionMin;
    public int breakMin;
    public int longBreakMin;
    public int sessionsBeforeLB;

    private int savedMinutes;
    private int savedSeconds;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    abstract void showTime(int minutes, int seconds);

    public void getSettings() {

        SettingsObject settings = settingsHelper.getSetings();
        workSessionMin = settings.intr[0];
        breakMin = settings.intr[1];
        longBreakMin = settings.intr[2];
        sessionsBeforeLB = settings.intr[3];
    }

    public void timerStart() {

        if (isWorkTime) {
            countDownTimer(0, 10);
            //countDownTimer(workSessionMin, 0);
            isWorkTime = false;
        } else if (breaksCount < sessionsBeforeLB) {
            countDownTimer(0, 3);
            //countDownTimer(breakMin, 0);
            breaksCount++;
            isWorkTime = true;
        } else {
            countDownTimer(0, 7);
            //countDownTimer(longBreakMin, 0);
            breaksCount = 0;
            isWorkTime = true;
        }
    }

    public void timerResume() {
        countDownTimer(savedMinutes,savedSeconds);
    }

    private void countDownTimer(int minutes, int seconds) {
        int intervalInSeconds = minutes * 60 + seconds;

        clearCompositeSubscription();

        Subscription s =
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v +1)
                        .take(intervalInSeconds)
                        .doAfterTerminate(() -> timerStart())
                        .subscribe(v -> {
                            savedMinutes = (int) (intervalInSeconds - v) / 60;
                            savedSeconds = (int) (intervalInSeconds - v) % 60;
                            showTime(savedMinutes,savedSeconds);
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