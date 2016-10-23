package com.bond.iampomodoro.di;

import com.bond.iampomodoro.presenter.SettingsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class ViewModule {

  @Provides
  @Singleton
  SettingsPresenter provideMainPresenter() {
    return new SettingsPresenter();
  }

  @Provides
  //@Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }

}
