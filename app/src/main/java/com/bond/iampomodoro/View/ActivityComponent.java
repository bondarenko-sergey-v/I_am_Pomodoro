package com.bond.iampomodoro.View;

import com.bond.iampomodoro.AppComponent;
import com.bond.iampomodoro.annotation.PerActivity;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(
    modules = { ActivityModule.class },
    dependencies = AppComponent.class)
public interface ActivityComponent extends BaseActivityComponent {

  //BusSubcomponent plus(BusModule module);
}
