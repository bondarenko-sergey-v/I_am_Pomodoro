package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.dataObjects.UserSettingsObject;
import com.bond.iampomodoro.view.fragments.SettingsView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class SettingsPresenter extends BasePresenter {

    private CompositeSubscription localCompositeSubscription = new CompositeSubscription();

    public void onCreate(SettingsView view) {
        App.getAppComponent().inject(this);

        Observable obs = view.showSettings(model.getUserSettings());

       localCompositeSubscription.add(
                obs.observeOn(AndroidSchedulers.mainThread())
                    .skip(1)
                    .subscribe(v -> model.setUserSettings((UserSettingsObject) v)));
    }

    public void onTabUnselected() {
        //model.setUserSettings(settings);
        //localCompositeSubscription.clear();
    }
}