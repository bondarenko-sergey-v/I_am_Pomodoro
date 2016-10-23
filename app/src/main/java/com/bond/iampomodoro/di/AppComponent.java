package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.fragments.FragmentSettings;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, ViewModule.class, PresenterModule.class })
public interface AppComponent {

//    SettingsPresenter mainPresenter();
//
//    Application provideApplication();

//    void inject (MainActivity activity);

    void inject (FragmentSettings fragmentSettings);

    void inject (SettingsPresenter mainPresenter);

    void inject (SettingsHelper settingsHelper);

    //@ApplicationContext
    //Context context();
}
