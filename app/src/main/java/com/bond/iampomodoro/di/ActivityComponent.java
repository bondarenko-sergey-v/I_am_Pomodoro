package com.bond.iampomodoro.di;

import android.app.Activity;

import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.model.Vibration;
import com.bond.iampomodoro.presenter.BasePresenter;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.util.VibrationUtil;
import com.bond.iampomodoro.view.MainActivity;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Component (dependencies = { AppComponent.class }, modules = { ActivityModule.class })

public interface ActivityComponent {

    //Activity activity();

    void inject(MainActivity mainActivity);

    void inject(Vibration vibration);

    //void inject (MainActivity mainActivity);

    //void inject (BasePresenter basePresenter);

}
