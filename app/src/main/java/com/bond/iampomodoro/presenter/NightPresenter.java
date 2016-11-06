package com.bond.iampomodoro.presenter;

import android.view.WindowManager;

import com.bond.iampomodoro.model.dto.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.NightView;

public class NightPresenter extends BasePresenter {

    private NightView view;
    private UserSettingsObject usrSet;
    private String timerState;

    public void onCreate(NightView view) {
        this.view = view;

        showActualButtons(behaviorSubject.getValue().timerState);
    }

    public void onTabSelected() {
        compositeSubscription.clear();
        this.usrSet = model.getUserSettings();
        keepScreenOn(usrSet.bool[5]); // NightKeepScreenOn

        if (view != null) {
            compositeSubscription.add(
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

    public void onStartButtonClick() {

        if(timerState.equals("onReset") || timerState.equals("onPause")) {
            view.showButtons("Start");
            view.showImage(false,0);
            model.startTimer();
        } else {
            view.showButtons("Pause");
            view.showImage(usrSet.bool[6],0); //TODO rewrite showImage
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