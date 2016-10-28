package com.bond.iampomodoro.di;

import android.app.Activity;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.ApplicationContext;
import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.model.SettingsHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

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
  @ActivityContext
  Activity provideActivity() {
    return this.activity;
  }

  @Inject
  @ApplicationContext
  Context context;

  @Provides
  @ApplicationContext
  Context provideContext() {
    return context;
  }
//  @Provides
//  @PerActivity
//  @ActivityContext
//  Context provideContextActivity() {
//    return mActivity;
//  }

//  @Provides
//  @PerActivity
//  SettingsHelper provideSettingsHelper() {
//    return new SettingsHelper();
//  }
}

//TODO Fix Dagger2 annotations and scopes!