package com.bond.iampomodoro.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.os.Vibrator;
import android.view.WindowManager;

import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.view.MainActivity;

import javax.inject.Inject;

public final class KeepScreenOn {

   @Inject
   @ActivityContext
   Activity context;

   public KeepScreenOn() {
       MainActivity.getActivityComponent().inject(this);
       //App.getComponent().inject(this);
   }

   public void keep(boolean keepScreenOn) {

       if(keepScreenOn) {
           context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       } else {
           context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       }
   }
}
