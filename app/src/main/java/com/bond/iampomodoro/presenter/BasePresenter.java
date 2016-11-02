package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.ModelImpl;
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
    BehaviorSubject<Integer> behaviorSubject;

    @Inject
    CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onStop() {
        //compositeSubscription.clear();
    }
}