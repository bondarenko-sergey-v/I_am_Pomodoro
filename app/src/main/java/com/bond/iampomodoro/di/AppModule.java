package com.bond.iampomodoro.di;

import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ApplicationContext;
import com.bond.iampomodoro.model.Model;
import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.model.dataObjects.TimerObject;
import com.bond.iampomodoro.view.util.NotifyUser;
import com.bond.iampomodoro.model.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

@Module
public class AppModule {

  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return this.application;
  }

  @Provides
  @Singleton
  @ApplicationContext
  Context provideContext() {
    return this.application;
  }

  @Provides
  @Singleton
  PreferencesHelper providePreferencesHelper() {
    return new PreferencesHelper(application);
  }

  @Provides
  @Singleton
  NotifyUser notifyUser() {
    return new NotifyUser(application);
  }

  @Provides
  @Singleton
  Model provideModelImpl() {
    return new ModelImpl();
  }

  @Provides
  @Singleton
  BehaviorSubject<TimerObject> provideBehaviorSubject() {
    return BehaviorSubject.create();
  }

  @Provides
  @Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }
}