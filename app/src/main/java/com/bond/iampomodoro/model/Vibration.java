package com.bond.iampomodoro.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.di.annotations.ApplicationContext;
import com.bond.iampomodoro.view.MainActivity;

import javax.inject.Inject;

public class Vibration {

 //   @Inject
 //   @ApplicationContext
 //   Context appContext;

    @Inject
    @ActivityContext
    Activity appContext;

    public Vibration() {
        MainActivity.getActivityComponent().inject(this);
        //App.getComponent().inject(this);
    }

    public void vibrate() {

        long[] pattern = { 0, 300, 400, 300 };
        Vibrator vibrator = (Vibrator) appContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, -1);

    }
}