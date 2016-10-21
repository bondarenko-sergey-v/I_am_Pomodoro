package com.bond.iampomodoro;

import android.app.Application;

public class App extends Application {

  private static AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();

    //component = buildComponent();
    //this.component = createComponent();
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
