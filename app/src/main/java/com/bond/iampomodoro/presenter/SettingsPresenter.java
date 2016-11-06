package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.model.dto.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.SettingsView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class SettingsPresenter extends BasePresenter {

    private SettingsView view;

    public void onCreate(SettingsView view) {
        this.view = view;

        onTabSelected();
    }

    public void onTabSelected() {
        Observable obs = view.showSettings(model.getUserSettings());

        compositeSubscription.add(
                obs.observeOn(AndroidSchedulers.mainThread())
                    .skip(1)
                    .subscribe(v -> model.setUserSettings((UserSettingsObject) v)));
    }
}