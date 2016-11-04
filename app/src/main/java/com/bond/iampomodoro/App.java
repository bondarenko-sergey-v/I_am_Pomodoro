package com.bond.iampomodoro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.di.ActivityComponent;
import com.bond.iampomodoro.di.ActivityModule;
import com.bond.iampomodoro.di.AppComponent;
import com.bond.iampomodoro.di.AppModule;
import com.bond.iampomodoro.di.DaggerAppComponent;
import com.bond.iampomodoro.view.MainActivity;

import javax.inject.Inject;

public class App extends Application {

  private static AppComponent appComponent;
  //private static ActivityComponent activityComponent;

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }

  @Inject
  MainActivity activity;

  @Override
  public void onCreate() {
    super.onCreate();

    appComponent = createComponent();
    //activityComponent = createActivityComponent();
  }

  public AppComponent createComponent() {
    return DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }

//  public ActivityComponent createActivityComponent() {
//
//    if(activityComponent == null) {
//      activityComponent = appComponent.plus(new ActivityModule(activity));
//    }
//    return activityComponent;
//  }

//  public static ActivityComponent getActivityComponent() {
//    return activityComponent;
//  }
// public void clearActivityComponent() {
//   // end lifecycle of chatComponent
//   activityComponent = null;
// }
}