package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.model.PreferencesHelper;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.fragments.FragmentHardcore;
import com.bond.iampomodoro.view.fragments.FragmentSettings;
import com.bond.iampomodoro.view.util.KeepScreenOn;
import com.bond.iampomodoro.view.util.NotifyUser;

import dagger.Subcomponent;

@PerActivity
@Subcomponent( modules = ActivityModule.class )

public interface ActivityComponent {

    void inject (MainActivity mainActivity);


    void inject (FragmentDay fragmentDay);

    void inject (FragmentHardcore fragmentHardcore);

    void inject (FragmentSettings fragmentSettings);

    //void inject (SettingsPresenter settingsPresenter);

    //void inject (DayPresenter dayPresenter);

    //void inject (HardcorePresenter hardcorePresenter);

    void inject (BasePresenter basePresenter);
}
