package com.bond.iampomodoro.di;

import com.bond.iampomodoro.di.annotations.PerActivity;
import com.bond.iampomodoro.view.MainActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent( modules = ActivityModule.class )

public interface ActivityComponent {

    void inject (MainActivity mainActivity);

}
