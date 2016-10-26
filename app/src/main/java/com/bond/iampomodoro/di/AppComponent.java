package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.fragments.FragmentHardcore;
import com.bond.iampomodoro.view.fragments.FragmentSettings;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, PresenterModule.class, ModelModule.class })
public interface AppComponent {

    void inject (FragmentSettings fragmentSettings);

    void inject (FragmentDay fragmentDay);

    void inject (FragmentHardcore fragmentHardcore);

    void inject (SettingsPresenter settingsPresenter);

    //void inject (DayPresenter dayPresenter);

    //void inject (HardcorePresenter hardcorePresenter);

    void inject (BasePresenter basePresenter);

    void inject (SettingsHelper settingsHelper);
}
