package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.NotifyUser;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.util.KeepScreenOn;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

  @Provides
  @Singleton
  SettingsPresenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }

  @Provides
  @Singleton
  NotifyUser provideNotifyUser() {
    return new NotifyUser();
  }

  @Provides
  @Singleton
  DayPresenter provideDayPresenter() {
    return new DayPresenter();
  }

  @Provides
  @Singleton
  HardcorePresenter provideHardcorePresenter() {
    return new HardcorePresenter();
  }

  @Provides
  @Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }

//  @Inject
//  KeepScreenOn keepScreenOn;

  @Provides
  KeepScreenOn provideKeepScreenOn() {
    return new KeepScreenOn();
  }

}
