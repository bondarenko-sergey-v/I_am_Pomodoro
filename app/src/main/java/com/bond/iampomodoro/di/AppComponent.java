package com.bond.iampomodoro.di;

import com.bond.iampomodoro.model.NotifyUser;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.view.MainActivity;
import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.fragments.FragmentHardcore;
import com.bond.iampomodoro.view.fragments.FragmentSettings;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, PresenterModule.class, ModelModule.class })
//@Component(modules = { AppModule.class })
public interface AppComponent {

   // Context context();
//
   // SettingsHelper settingsHelper();
//
   // SettingsPresenter settingsPresenter();
//
   // DayPresenter dayPresenter();
//
   // HardcorePresenter hardcorePresenter();
//
   // CompositeSubscription compositeSubscription();
//
   // NotifyUser vibration();

//    void inject (App app);
   //void inject (MainActivity mainActivity);
//
   void inject (FragmentSettings fragmentSettings);

   void inject (FragmentDay fragmentDay);

   void inject (FragmentHardcore fragmentHardcore);

   void inject (SettingsPresenter settingsPresenter);

   void inject (DayPresenter dayPresenter);

   void inject (HardcorePresenter hardcorePresenter);

   void inject (BasePresenter basePresenter);

   void inject (SettingsHelper settingsHelper);

   void inject (NotifyUser notifyUser);
}
