package com.bond.iampomodoro.di;

import android.app.Activity;

import com.bond.iampomodoro.di.annotations.PerActivity;

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
}