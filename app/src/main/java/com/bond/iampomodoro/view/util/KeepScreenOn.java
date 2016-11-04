package com.bond.iampomodoro.view.util;

import android.app.Activity;
import android.view.WindowManager;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.view.MainActivity;

public class KeepScreenOn {

    private final Activity activity;

    public KeepScreenOn(Activity activity) {
        //App.getActivityComponent().inject(this);
        //App.getAppComponent().inject(this);
        this.activity = activity;
    }

    public void keep(boolean keepScreenOn) {

        if(keepScreenOn) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}