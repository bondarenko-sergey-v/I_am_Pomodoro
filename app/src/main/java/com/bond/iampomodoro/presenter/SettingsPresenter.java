package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.SettingsView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class SettingsPresenter extends BasePresenter {

    private SettingsView view;
    //private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    public void onCreate(SettingsView view) {
        //MainActivity.getActivityComponent().inject(this);
        this.view = view;

        onTabSelected(); //TODO Make only UI changes, without subscriptions
    }

    public void onTabSelected() {
        Observable obs = view.showSettings(model.getUserSettings());

        //localCompositeSubscription.add(
        compositeSubscription.add(
                obs.observeOn(AndroidSchedulers.mainThread())
                    .skip(1)
                    .subscribe(v -> model.setUserSettings((UserSettingsObject) v)));
    }

    public void onTabUnselected() {
        //localCompositeSubscription.clear();
    }
}