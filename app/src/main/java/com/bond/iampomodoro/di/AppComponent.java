package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.view.MainActivity;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    ActivityComponent plus(ActivityModule module);

    void inject (MainActivity mainActivity);

    void inject (ModelImpl modelImpl);
}