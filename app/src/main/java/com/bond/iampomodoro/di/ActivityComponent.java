package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.fragments.FragmentNight;
import com.bond.iampomodoro.view.fragments.FragmentSettings;

import dagger.Subcomponent;

@PerActivity
@Subcomponent( modules = ActivityModule.class )

public interface ActivityComponent {

    void inject (MainActivity mainActivity);

    void inject (FragmentDay fragmentDay);

    void inject (FragmentNight fragmentNight);

    void inject (FragmentSettings fragmentSettings);

    void inject (BasePresenter basePresenter);
}
