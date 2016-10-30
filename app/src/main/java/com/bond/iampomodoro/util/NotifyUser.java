package com.bond.iampomodoro.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.bond.iampomodoro.R;

public class NotifyUser {

    private final Context context;

    public NotifyUser(Context context) {
        this.context = context;
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