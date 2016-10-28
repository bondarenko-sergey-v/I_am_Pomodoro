package com.bond.iampomodoro.presenter;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentHardcoreBinding;
import com.bond.iampomodoro.di.annotations.ActivityContext;
import com.jakewharton.rxbinding.view.RxView;

import javax.inject.Inject;
import javax.inject.Named;

public class HardcorePresenter extends BasePresenter {

    @Inject
    //@Named("ActivityContext")
    @ActivityContext
    Context activityContext;

    private FragmentHardcoreBinding binding;

    public void notifyHardcoreFragmentStarts(FragmentHardcoreBinding binding) {
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
        binding.minutes.setText(String.valueOf(
                (int) timelInSeconds / 60));
        binding.seconds.setText(String.format("%02d",//TODO Check warning
                (int) timelInSeconds % 60));
    }

    @Override
    void notifyUser(Context context) {
        long[] pattern = { 500, 300, 400, 200 };
        Vibrator vibrator = (Vibrator) activityContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, -1);
    }
}