package com.bond.iampomodoro;

import android.app.Application;
import android.content.Context;
import android.os.Vibrator;

import com.bond.iampomodoro.di.AppComponent;
import com.bond.iampomodoro.di.AppModule;
import com.bond.iampomodoro.di.DaggerAppComponent;
import com.bond.iampomodoro.di.ModelModule;
import com.bond.iampomodoro.di.PresenterModule;

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
            //.modelModule(new ModelModule())
            //.presenterModule(new PresenterModule())
            .build();
  }

  public static AppComponent getComponent() {
    return component;
  }
}