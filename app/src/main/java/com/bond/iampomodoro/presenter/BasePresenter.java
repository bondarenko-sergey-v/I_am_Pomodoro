package com.bond.iampomodoro.presenter;

import android.app.Activity;

import com.bond.iampomodoro.model.Model;
import com.bond.iampomodoro.model.dto.TimerObject;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.util.NotifyUser;

import javax.inject.Inject;

import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    Model model;
    @Inject
    NotifyUser notifyUser;
    @Inject
    Activity activity;
    @Inject
    BehaviorSubject<TimerObject> behaviorSubject;
    @Inject
    CompositeSubscription compositeSubscription;

    public BasePresenter() {
        MainActivity.getActivityComponent().inject(this);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}