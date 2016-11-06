package com.bond.iampomodoro;

import android.app.Application;

import com.bond.iampomodoro.di.AppComponent;
import com.bond.iampomodoro.di.AppModule;
import com.bond.iampomodoro.di.DaggerAppComponent;

public class App extends Application {

  private static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }
}