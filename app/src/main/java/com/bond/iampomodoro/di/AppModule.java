package com.bond.iampomodoro.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.ApplicationContext;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.Vibration;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

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
//  Vibration provideVibration() {
//    return new Vibration();
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
