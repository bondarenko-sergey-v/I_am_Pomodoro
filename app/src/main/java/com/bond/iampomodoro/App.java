package com.bond.iampomodoro;

import android.app.Application;

import com.bond.iampomodoro.di.AppComponent;
import com.bond.iampomodoro.di.AppModule;
import com.bond.iampomodoro.di.DaggerAppComponent;

public class App extends Application {

  private static AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();

    component = createComponent();
  }

  public AppComponent createComponent() {
    return DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent getComponent() {
    return component;
  }
}