package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.model.NotifyUser;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.util.KeepScreenOn;
import com.bond.iampomodoro.view.MainActivity;

import dagger.Component;

@PerActivity
@Component (dependencies = { AppComponent.class }, modules = { ActivityModule.class })

public interface ActivityComponent {

    //Activity activity();

    void inject (MainActivity mainActivity);

    void inject (NotifyUser notifyUser);

    void inject (KeepScreenOn keepScreenOn);

    //void inject (MainActivity mainActivity);

    //void inject (BasePresenter basePresenter);

    //void inject (DayPresenter dayPresenter);

    //void inject (HardcorePresenter hardcorePresenter);

}
