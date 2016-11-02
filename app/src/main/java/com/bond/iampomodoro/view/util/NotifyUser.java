package com.bond.iampomodoro.view.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;

public class NotifyUser {

    private final Context context;

    public NotifyUser(Context context) {
        App.getAppComponent().inject(this); //TODO ???
        this.context = context;
    }

    public void playSoundAndVibrate(boolean playSound, boolean vibrate) {

        if(playSound) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.beep3);
            mp.setOnPreparedListener(MediaPlayer::start);
            mp.setOnCompletionListener(MediaPlayer::release);
        }

        if(vibrate) {
            long[] pattern = { 0, 300, 400, 300 };
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, -1);
        }
    }
}