package com.bond.iampomodoro;

import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.view.ActivityModule;
import com.bond.iampomodoro.view.fragments.FragmentSettings;
import com.bond.iampomodoro.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, ActivityModule.class })
public interface AppComponent {

//    Presenter mainPresenter();
//
//    Application provideApplication();

//    void inject (MainActivity activity);

    void inject (FragmentSettings fragmentSettings);

    void inject (Presenter mainPresenter);

    //void inject (SettingsHelper settingsHelper);
}
