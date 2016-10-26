package com.bond.iampomodoro.di;

import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
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
  DayPresenter provideDayPresenter() {
    return new DayPresenter();
  }

//  @Provides
//  @Singleton
//  BasePresenter provideBasePresenter() {
//    return new BasePresenter();
//  }

  @Provides
  //@Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }

}
