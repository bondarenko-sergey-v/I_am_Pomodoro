package com.bond.iampomodoro.view;

import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.view.fragments.FragmentSettings;
import com.bond.iampomodoro.annotation.PerActivity;

import dagger.Component;

@PerActivity
@Component(
    modules = { ActivityModule.class })

//public interface ActivityComponent extends BaseActivityComponent {
public interface ActivityComponent {

    void inject (FragmentSettings fragmentSettings);

//    void inject (Presenter mainPresenter);

}
