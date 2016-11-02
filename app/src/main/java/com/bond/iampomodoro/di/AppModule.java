package com.bond.iampomodoro.di;

import android.app.Application;
import android.content.Context;

import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.view.util.NotifyUser;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.fragments.FragmentDay;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

@Module
public class AppModule {

  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return this.application;
  }

  @Provides
  @Singleton
  SettingsHelper provideSettingsHelper() {
    return new SettingsHelper(application);
  }

  @Provides
  @Singleton
  NotifyUser notifyUser() {
    return new NotifyUser(application);
  }

//  @Provides
//  @Singleton
//  FragmentDayBinding getFragmentDayBinding() {
//    return this.fragmentDayBinding = FragmentDay.getFragmentDayBinding(); }

  @Provides
  @Singleton
  DayPresenter provideDayPresenter() {
    return new DayPresenter();
  }

  @Provides
  @Singleton
  ModelImpl provideModelImpl() {
    return new ModelImpl();
  }

  @Provides
  @Singleton
  HardcorePresenter provideHardcorePresenter() {
    return new HardcorePresenter();
  }

//  @Provides
//  BasePresenter provideBasePresenter() {
//    return new BasePresenter()
//  }

  @Provides
  @Singleton
  SettingsPresenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }

  @Provides
  @Singleton
  CompositeSubscription provideCompositeSubscription() {
    return new CompositeSubscription();
  }

  @Provides
  @Singleton
  BehaviorSubject<Integer> provideBehaviorSubject() {
    return BehaviorSubject.create();
  }

  @Provides
  @Singleton
  FragmentDay providesFragmentDay() {
    return new FragmentDay();
  }
//  @Provides
//  @Singleton
//  CDTimer provideTimer() { return new CDTimer(); }
}