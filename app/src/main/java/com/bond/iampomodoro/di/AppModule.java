package com.bond.iampomodoro.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  protected final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides
  //@ApplicationContext
  Context provideContext() {
    return mApplication;
  }

}
