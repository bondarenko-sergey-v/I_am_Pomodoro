package com.bond.iampomodoro.presenter;

import android.view.WindowManager;

import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.DayView;

import rx.subscriptions.CompositeSubscription;

public class DayPresenter extends BasePresenter {

    //private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private DayView view;
    private UserSettingsObject usrSet;
    private String timerState;

    public void onCreate(DayView view) {
        //MainActivity.getActivityComponent().inject(this);
        this.view = view;

        showActualButtons(behaviorSubject.getValue().timerState);
    }

    public void onTabSelected() {
        //localCompositeSubscription.clear();
        compositeSubscription.clear();
        this.usrSet = model.getUserSettings();
        keepScreenOn(usrSet.bool[2]); // DayKeepScreenOn

        if (view != null) {
            compositeSubscription.add( //TODO Fix bug - on start call twice
                behaviorSubject.subscribe(v -> {
                    view.showTime(v.intervalInSeconds);

                    if(v.intervalInSeconds == 0) {
                        notifyUser.playSoundAndVibrate(usrSet.bool[0], usrSet.bool[1]);
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
                view.showButtons("Pause");
                break;
            case "onReset":
                view.showButtons("Reset");
                break;
            default:
                view.showButtons("Start");
                break;
        }
    }

    public void onTabUnselected() {
        //localCompositeSubscription.clear();
    }

    public void onStartButtonClick() {

        if(timerState.equals("onReset") || timerState.equals("onPause")) {
            view.showButtons("Start");
            model.startTimer();
        } else {
            view.showButtons("Pause");
            model.pauseTimer();
        }
    }

    public void onResetButtonClick() {
        view.showButtons("Reset");
        model.resetTimer();
    }

    private void keepScreenOn(boolean keepScreenOn) {

        if(keepScreenOn) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}