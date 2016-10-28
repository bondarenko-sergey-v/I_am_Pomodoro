package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerApplication;
import com.bond.iampomodoro.model.SettingsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

  @Provides
  @Singleton
  SettingsHelper provideSettingsHelper() {
    return new SettingsHelper();
  }
}
