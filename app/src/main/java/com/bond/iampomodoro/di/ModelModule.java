package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.SettingsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

  @Provides
    //@PerActivity
  @Singleton
  SettingsHelper provideSettingsHelper() {
    return new SettingsHelper();
  }
}
