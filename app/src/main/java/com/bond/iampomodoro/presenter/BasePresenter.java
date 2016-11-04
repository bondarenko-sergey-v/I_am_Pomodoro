package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.model.dataObjects.TimerObject;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.util.KeepScreenOn;
import com.bond.iampomodoro.view.util.NotifyUser;

import javax.inject.Inject;

import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    ModelImpl model;
    @Inject
    NotifyUser notifyUser;
    @Inject
    KeepScreenOn keepScreenOn;
    @Inject
    BehaviorSubject<TimerObject> behaviorSubject;
    @Inject
    CompositeSubscription compositeSubscription;

    public BasePresenter() {
        MainActivity.getActivityComponent().inject(this);
    }

    @Override
    public void onStop() {
        //compositeSubscription.clear();
    }
}