package com.bond.iampomodoro.di;

import com.bond.iampomodoro.presenter.Presenter;
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
  Presenter providePresenter() {
    return new Presenter();
  }

  @Provides
  //@Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }

}
