package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.DayView;

import rx.subscriptions.CompositeSubscription;

public class DayPresenter extends BasePresenter {

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    private DayView view;
    private String timerState;

    public void onCreate(DayView view) {
        App.getAppComponent().inject(this);
        this.view = view;

     //   PreferencesObject n = new PreferencesObject(true,true,true,false,true,true,true,25,5,15,4,0,1,"onReset");
     //   System.out.println(new Gson().toJson(n));
    }

    public void onTabSelected() {
        localCompositeSubscription.clear();

        if (view != null) {
            localCompositeSubscription.add( //TODO Fix bug - on start call twice
                behaviorSubject.subscribe(v -> {
                    view.showTime(v.intervalInSeconds);

                    if(v.intervalInSeconds == 0) {
                        UserSettingsObject usrSet = model.getUserSettings(); // TODO ? Refactor to this.usrSet
                        notifyUser.playSoundAndVibrate(usrSet.bool[0], usrSet.bool[1]);
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