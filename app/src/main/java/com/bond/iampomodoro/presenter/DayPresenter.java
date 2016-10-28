package com.bond.iampomodoro.presenter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.jakewharton.rxbinding.view.RxView;

public class DayPresenter extends BasePresenter {

    private FragmentDayBinding binding;

 //   public DayPresenter() {
 //       App.getComponent().inject(this);
 //   }

    public void notifyDayFragmentStarts(FragmentDayBinding binding) {
        this.binding = binding;

        getSettings();

        initUI();
    }

    private void initUI() {

        RxView.clicks(binding.startBtn)
            .subscribe(v -> {
                if(ob.isTimerOnPause || ob.timerCycleCounter == 0) {
                    startTimer();
                } else {
                    pauseTimer();
                }
            });

        RxView.clicks(binding.resetBtn)
            .subscribe(v -> resetTimer());
    }

    @Override
    public void startTimer() {
        showTime(ob.intervalInSeconds);
        timerStart();

        binding.startBtn.setText(R.string.pause);
        binding.resetBtn.setEnabled(true);
        binding.resetBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void pauseTimer() {
        clearCompositeSubscription();
        showTime(ob.intervalInSeconds);
        ob.isTimerOnPause = true;

        binding.startBtn.setText(R.string.start);
    }

    @Override
    public void resetTimer() {
        clearCompositeSubscription();

        ob.timerCycleCounter = 0;
        ob.isTimerOnPause = false;
        ob.intervalInSeconds = workSessionMin * 60;
        showTime(ob.intervalInSeconds);

        binding.startBtn.setText(R.string.start);
        binding.resetBtn.setEnabled(false);
        binding.resetBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTime(int timelInSeconds) {
        if(timelInSeconds == 0) {
            binding.minutes.setText(String.valueOf(0));
            binding.seconds.setText(String.format("%02d", 0));
            return;
        }

        binding.minutes.setText(String.valueOf(
                (int) timelInSeconds / 60));
        binding.seconds.setText(String.format("%02d",//TODO Check warning
                (int) timelInSeconds % 60));
    }

    @Override
    public void notifyUser(Context context) {


    //    long[] pattern = { 0, 300, 400, 300 };
    //    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    //    vibrator.vibrate(pattern, -1);

        MediaPlayer mp = MediaPlayer.create(context, R.raw.beep3);
        mp.setOnPreparedListener(MediaPlayer::start);
        mp.setOnCompletionListener(MediaPlayer::release);


    }
}