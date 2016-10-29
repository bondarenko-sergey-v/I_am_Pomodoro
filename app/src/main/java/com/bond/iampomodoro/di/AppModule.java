package com.bond.iampomodoro.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  //protected final Application application;
  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

//  @Provides
//  @Singleton
//  Application provideApplication() {
//    return this.application;
//  }

  @Provides
  @Singleton
  @ApplicationContext
  Context provideContext() {
    return this.application;
  }

  @Inject
  @ActivityContext
  Activity appContext;

  @Provides
  @ActivityContext
  Activity provideActivity() {
    return appContext;
  }
  //----------------
//
//  @Provides
//  @Singleton
//  SettingsHelper provideSettingsHelper() {
//    return new SettingsHelper();
//  }
//
//  @Provides
//  @Singleton
//  SettingsPresenter provideSettingsPresenter() {
//    return new SettingsPresenter();
//  }
//
//  @Provides
//  @Singleton
//  NotifyUser provideVibration() {
//    return new NotifyUser();
//  }
//
//  @Provides
//  @Singleton
//  DayPresenter provideDayPresenter() {
//    return new DayPresenter();
//  }
//
//  @Provides
//  @Singleton
//  HardcorePresenter provideHardcorePresenter() {
//    return new HardcorePresenter();
//  }
//
//  @Provides
//  @Singleton
//  CompositeSubscription provideCompositeSubscription() {
//    return new CompositeSubscription();
//  }

}
