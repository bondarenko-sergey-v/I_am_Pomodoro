package com.bond.iampomodoro.View;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides
  //@PerActivity
  public AppCompatActivity provideActivity() {
    return activity;
  }

}
