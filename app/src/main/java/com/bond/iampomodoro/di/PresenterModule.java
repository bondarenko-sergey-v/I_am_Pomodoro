package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerApplication;
import com.bond.iampomodoro.model.Vibration;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;

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
  Vibration provideVibration() {
    return new Vibration();
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

}
