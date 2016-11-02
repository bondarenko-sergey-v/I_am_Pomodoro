package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.model.TimerSettingsObject;
import com.bond.iampomodoro.view.fragments.DayView;

import rx.subscriptions.CompositeSubscription;

public class DayPresenter extends BasePresenter {

    private TimerSettingsObject ob;
    private SettingsObject settings;

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private DayView view;

    public void onCreate(DayView view) {
        App.getAppComponent().inject(this);
        this.view = view;

        onTabSelected();
    }

    public void onTabSelected() {
        ob = model.getTimerState();
        settings = model.getSettings();

        localCompositeSubscription.add(
                behaviorSubject.subscribe(v -> {
                    view.showTime(v);
                    if(v == 0) {
                        notifyUser.playSoundAndVibrate(settings.bool[0], settings.bool[1]);
                    }
                }));

        switch (ob.timerState) {
            case "onPause":
                view.showButons("Pause");
                break;
            case "onReset":
                view.showButons("Reset");
                view.showTime(settings.intr[0] * 60);
                break;
            default:
                view.showButons("Start");
                break;
        }
    }

    public void onTabUnselected() {
        localCompositeSubscription.clear();
    }

    public void onStartButtonClick() {
        ob = model.getTimerState();

        if(!ob.timerState.equals("onStart")) {
            model.startTimer();
            view.showButons("Start");
        } else {
            model.pauseTimer();
            view.showButons("Pause");
        }
    }

    public void onResetButtonClick() {
        model.resetTimer();
        view.showButons("Reset");
        view.showTime(settings.intr[0] * 60);
    }

    public void onSaveInstanceState() {
        //TODO Make it
    }
}