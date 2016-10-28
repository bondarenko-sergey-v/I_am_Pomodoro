package com.bond.iampomodoro.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.os.Vibrator;

import com.bond.iampomodoro.di.annotations.ActivityContext;

import javax.inject.Inject;

public final class VibrationUtil {

    @Inject
    //@Named("ActivityContext")
    @ActivityContext
    Context activityContext;

    private final Context mContext = activityContext;

    public static void vibrate() {
    //    long[] pattern = { 500, 300, 400, 200 };
    //    Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    //    vibrator.vibrate(pattern, -1);
    }
}
