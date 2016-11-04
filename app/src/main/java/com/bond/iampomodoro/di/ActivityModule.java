package com.bond.iampomodoro.di;

import android.app.Activity;
import android.content.Context;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.model.ModelImpl;
import com.bond.iampomodoro.model.PreferencesHelper;
import com.bond.iampomodoro.model.dataObjects.TimerObject;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.util.KeepScreenOn;
import com.bond.iampomodoro.view.util.NotifyUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;


@Module
public class ActivityModule {

  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  @PerActivity
  Activity provideActivity() {
    return this.activity;
  }

  @Provides
  @ActivityContext
  Context providesContext() {
    return this.activity;
  }

  @Provides
  @PerActivity
  KeepScreenOn keepScreenOn() {
    return new KeepScreenOn(activity);
  }

  @Provides
  @PerActivity
  DayPresenter provideDayPresenter() {
    return new DayPresenter();
  }

  @Provides
  @PerActivity
  HardcorePresenter provideHardcorePresenter() {
    return new HardcorePresenter();
  }

  @Provides
  @PerActivity
  SettingsPresenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }

}