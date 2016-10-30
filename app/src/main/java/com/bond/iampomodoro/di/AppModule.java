package com.bond.iampomodoro.di;

import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.bond.iampomodoro.util.NotifyUser;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.fragments.FragmentDay;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class AppModule {

  private final Application application;
  private FragmentDayBinding fragmentDayBinding;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return this.application;
  }

  @Provides
  @Singleton
  SettingsHelper provideSettingsHelper() {
    return new SettingsHelper(application);
  }

  @Provides
  @Singleton
  NotifyUser notifyUser() {
    return new NotifyUser(application);
  }

  @Provides
  @Singleton
  FragmentDayBinding getFragmentDayBinding() {
    return this.fragmentDayBinding = FragmentDay.getFragmentDayBinding(); }

  @Provides
  @Singleton
  DayPresenter provideDayPresenter() {
    return new DayPresenter(fragmentDayBinding);
  }

  @Provides
  @Singleton
  HardcorePresenter provideHardcorePresenter() {
    return new HardcorePresenter();
  }

  @Provides
  @Singleton
  SettingsPresenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }

  @Provides
  @Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }
}