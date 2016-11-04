package com.bond.iampomodoro;

import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.di.AppComponent;
import com.bond.iampomodoro.di.AppModule;
import com.bond.iampomodoro.di.DaggerAppComponent;

public class App extends Application {

  private static AppComponent appComponent;
  //private static ActivityComponent activityComponent;

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }


  @Override
  public void onCreate() {
    super.onCreate();

    appComponent = createComponent();
  }

  public AppComponent createComponent() {
    return DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }

//  public static ActivityComponent plusActivityComponent(Activity activity) {
//    // always get only one instance
//    if (activityComponent == null) {
//      // start lifecycle of chatComponent
//      activityComponent = appComponent.plusActivityComponent(new ActivityModule(activity));
//    }
//    return activityComponent;
//  }
//
//  public void clearActivityComponent() {
//    // end lifecycle of chatComponent
//    activityComponent = null;
//  }
}