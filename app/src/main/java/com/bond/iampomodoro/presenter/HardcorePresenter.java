package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.model.TimerSettingsObject;
import com.bond.iampomodoro.view.fragments.HardcoreView;

import rx.subscriptions.CompositeSubscription;

public class HardcorePresenter extends BasePresenter {

    private TimerSettingsObject ob;
    private SettingsObject settings;

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private HardcoreView view;

    public void onCreate(HardcoreView view) {
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
                        notifyUser.playSoundAndVibrate(settings.bool[3], settings.bool[4]);
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

    }
}