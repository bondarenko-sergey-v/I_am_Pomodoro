package com.bond.iampomodoro.model;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.bond.iampomodoro.view.MainActivity;

import javax.inject.Inject;

public class NotifyUser {

    @Inject
    @ActivityContext
    Activity context;

    public NotifyUser() {
        MainActivity.getActivityComponent().inject(this);
        //App.getComponent().inject(this);
    }

    public void vibrateAndPlaySound(boolean playSound, boolean vibrate) {

        if(vibrate) {
            long[] pattern = { 0, 300, 400, 300 };
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, -1);
        }

        if(playSound) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.beep3);
            mp.setOnPreparedListener(MediaPlayer::start);
            mp.setOnCompletionListener(MediaPlayer::release);
        }
    }
}