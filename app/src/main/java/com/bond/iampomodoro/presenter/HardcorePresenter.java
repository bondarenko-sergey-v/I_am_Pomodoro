package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.fragments.HardcoreView;

import rx.subscriptions.CompositeSubscription;

public class HardcorePresenter extends BasePresenter {

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private HardcoreView view;
    private UserSettingsObject usrSet;
    private String timerState;

    public void onCreate(HardcoreView view) {
        //MainActivity.getActivityComponent().inject(this);
        this.view = view;

        showActualButtons(behaviorSubject.getValue().timerState);
    }

    public void onTabSelected() {
        localCompositeSubscription.clear();
        this.usrSet = model.getUserSettings();
        //keepScreenOn.keep(usrSet.bool[5]); // NightKeepScreenOn

        if (view != null) {
            localCompositeSubscription.add(
                behaviorSubject.subscribe(v -> {
                    view.showTime(v.intervalInSeconds);

                    if(v.intervalInSeconds == 0) {
                        notifyUser.playSoundAndVibrate(usrSet.bool[3], usrSet.bool[4]);
                    }

                    if(!v.timerState.equals(timerState)) {
                        showActualButtons(v.timerState);
                        timerState = v.timerState;
                    }
            }));
        }
    }

    private void showActualButtons(String timerState) {
        switch (timerState) {
            case "onPause":
                view.showButons("Pause");
                break;
            case "onReset":
                view.showButons("Reset");
                break;
            default:
                view.showButons("Start");
                break;
        }
    }

    public void onTabUnselected() {
        //localCompositeSubscription.clear(); //TODO Move to onPause
    }

    public void onStartButtonClick() {

        if(timerState.equals("onReset") || timerState.equals("onPause")) {
            view.showButons("Start");
            model.startTimer();
        } else {
            view.showButons("Pause");
            model.pauseTimer();
        }
    }

    public void onResetButtonClick() {
        view.showButons("Reset");
        model.resetTimer();
    }

    public void onSaveInstanceState() {
        //TODO Make it
    }
}