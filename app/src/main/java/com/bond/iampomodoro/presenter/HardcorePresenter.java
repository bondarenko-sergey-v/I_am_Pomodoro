package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.HardcoreView;

import rx.subscriptions.CompositeSubscription;

public class HardcorePresenter extends BasePresenter {

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private HardcoreView view;
    private String timerState;

    public void onCreate(HardcoreView view) {
        App.getAppComponent().inject(this);
        this.view = view;
    }

    public void onTabSelected() {
        localCompositeSubscription.clear();

        if (view != null) {
            localCompositeSubscription.add(
                behaviorSubject.subscribe(v -> {
                    view.showTime(v.intervalInSeconds);

                    if(v.intervalInSeconds == 0) {
                        UserSettingsObject usrSet = model.getUserSettings(); // TODO ? Refactor to this.usrSet
                        notifyUser.playSoundAndVibrate(usrSet.bool[3], usrSet.bool[4]);
                    }

                    timerState = v.timerState;

                    switch (v.timerState) { //TODO - do switch only if v.timerState changes
                        case "onPause":
                            view.showButons("Pause");
                            break;
                        case "onReset":
                            view.showButons("Reset");
                            //view.showTime(v. * 60);
                            break;
                        default:
                            view.showButons("Start");
                            break;
                    }
            }));
        }
    }

    public void onTabUnselected() {
        //localCompositeSubscription.clear(); //TODO Move to onPause
    }

    public void onStartButtonClick() {

        if(timerState.equals("onReset") || timerState.equals("onPause")) {
            model.startTimer();
            //view.showButons("Start");
        } else {
            model.pauseTimer();
            //view.showButons("Pause");
        }
    }

    public void onResetButtonClick() {
        model.resetTimer();
        //view.showButons("Reset");
    }

    public void onSaveInstanceState() {
        //TODO Make it
    }
}