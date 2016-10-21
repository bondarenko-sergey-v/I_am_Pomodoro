package com.bond.iampomodoro;

import android.app.Application;

import com.bond.iampomodoro.Presenter.MainPresenter;
import com.bond.iampomodoro.View.FragmentSettings;
import com.bond.iampomodoro.View.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    MainPresenter mainPresenter();

    Application provideApplication();

    void inject (MainActivity activity);

    void inject (FragmentSettings fragmentSettings);

    void inject (MainPresenter mainPresenter);

    //void inject (SettingsHelper settingsHelper);
}
