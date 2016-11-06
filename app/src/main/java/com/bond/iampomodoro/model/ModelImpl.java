package com.bond.iampomodoro.model;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.dataObjects.PreferencesObject;
import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.model.dataObjects.TimerObject;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class ModelImpl implements Model {

    @Inject
    PreferencesHelper preferencesHelper;
    @Inject
    BehaviorSubject<TimerObject> behaviorSubject;
    //@Inject
    CompositeSubscription compositeSubscription
            = new CompositeSubscription(); /** LOCAL Composite Subscription **/

    private PreferencesObject pref;

    public ModelImpl() {
        App.getAppComponent().inject(this);
        this.pref = preferencesHelper.getPreferences();

        //TODO Change to preferences
        behaviorSubject.onNext(new TimerObject(pref.workSession * 60, pref.timerState));
    }

    @Override
    public void startTimer() {
        clearCompositeSubscription();

        if(pref.timerState.equals("onPause")) {
            pref.timerState = "onStart";
            initBehaviorSubject(behaviorSubject.getValue().intervalInSeconds);
            return;
        }

        pref.timerCycleCounter++;

        if (pref.timerCycleCounter % 2 != 0) {
            pref.timerState = "onWorkSession";
            //initBehaviorSubject(15);  // Mock
            initBehaviorSubject(pref.workSession * 60); // Start work session
        } else if (pref.timerCycleCounter % ((pref.sessionsBeforeLB + 1) * 2) != 0) {
            pref.timerState = "onBreak";
            //initBehaviorSubject(5);   // Mock
            initBehaviorSubject(pref.breakMin * 60);    // Start break
        } else {
            pref.timerState = "onLongBreak";
            //initBehaviorSubject(10);  // Mock
            initBehaviorSubject(pref.longBreak * 60);   // Start long break
        }

        preferencesHelper.setPreferences(pref); //TODO Move to onStop
    }

    @Override
    public void pauseTimer() {
        clearCompositeSubscription();
        pref.timerState = "onPause";
        behaviorSubject.onNext(new TimerObject(
                behaviorSubject.getValue().intervalInSeconds, pref.timerState));
        preferencesHelper.setPreferences(pref); //TODO Move to onStop
    }

    @Override
    public void resetTimer() {
        clearCompositeSubscription();

        pref.timerState = "onReset";
        pref.timerCycleCounter = 0;
        pref.intervalInSeconds = pref.workSession * 60;

        behaviorSubject.onNext(new TimerObject(pref.intervalInSeconds, pref.timerState));
        preferencesHelper.setPreferences(pref); //TODO Move to onStop
    }

    private void initBehaviorSubject(int intervalInSeconds) {

        compositeSubscription.add(
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v + 1)
                        .take(intervalInSeconds)
                        .map(v -> (int) (intervalInSeconds - v))
                        .map(v -> new TimerObject(v, pref.timerState))
                        .doOnCompleted(this::startTimer)
                        .subscribe(behaviorSubject::onNext));
    }

    @Override
    public UserSettingsObject getUserSettings() {
        Boolean[] b = {pref.daySound, pref.dayVibration, pref.dayKeepScreen,
                pref.nightSound, pref.nightVibration, pref.nightKeepScreen, pref.nightPictures};

        return new UserSettingsObject(b,pref.workSession, pref.breakMin,
                pref.longBreak, pref.sessionsBeforeLB);
    }

    @Override
    public void setUserSettings(UserSettingsObject usrSet) {
        pref.daySound = usrSet.bool[0];
        pref.dayVibration = usrSet.bool[1];
        pref.dayKeepScreen = usrSet.bool[2];
        pref.nightSound = usrSet.bool[3];
        pref.nightVibration = usrSet.bool[4];
        pref.nightKeepScreen = usrSet.bool[5];
        pref.nightPictures = usrSet.bool[6];

        pref.workSession = usrSet.workSession;
        pref.breakMin = usrSet.breakMin;
        pref.longBreak = usrSet.longBreak;
        pref.sessionsBeforeLB = usrSet.sessionsBeforeLB;

        preferencesHelper.setPreferences(pref);

        if(pref.timerState.equals("onReset")) {
            behaviorSubject.onNext(new TimerObject(pref.workSession * 60, pref.timerState));
        }
    }

    private void clearCompositeSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }
}