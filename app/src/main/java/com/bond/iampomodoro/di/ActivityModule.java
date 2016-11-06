package com.bond.iampomodoro.di;

import android.app.Activity;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.NightPresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  @PerActivity
  Activity provideActivity() {
    return this.activity;
  }

  @Provides
  @PerActivity
  @ActivityContext
  Context providesContext() {
    return this.activity;
  }

  @Provides
  @PerActivity
  DayPresenter provideDayPresenter() {
    return new DayPresenter();
  }

  @Provides
  @PerActivity
  NightPresenter provideNightPresenter() {
    return new NightPresenter();
  }

  @Provides
  @PerActivity
  SettingsPresenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }

}