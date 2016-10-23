package com.bond.iampomodoro.view;

import android.support.v7.app.AppCompatActivity;

import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.annotation.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

//  private final AppCompatActivity activity;

//  public ActivityModule(AppCompatActivity activity) {
//    this.activity = activity;
//  }

//  @Provides
//  //@PerActivity
//  public AppCompatActivity provideActivity() {
//    return activity;
//  }

  @Provides
  //@PerActivity
  Presenter provideMainPresenter() {
    return new Presenter();
  }

  @Provides
    //@PerActivity
  SettingsHelper provideSettingsHelper() {
    return new SettingsHelper();
  }
}
