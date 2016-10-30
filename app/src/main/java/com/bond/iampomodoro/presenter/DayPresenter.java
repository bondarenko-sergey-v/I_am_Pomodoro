package com.bond.iampomodoro.presenter;

import android.view.View;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.bond.iampomodoro.util.NotifyUser;
import com.jakewharton.rxbinding.view.RxView;

public class DayPresenter extends BasePresenter {

    private FragmentDayBinding binding;

    public DayPresenter(FragmentDayBinding binding) {
        this.binding = binding;
//        App.getAppComponent().inject(this);
          //MainActivity.getActivitySubcomponent().inject(this);
    }

    public void notifyDayFragmentStarts() {

        getSettingsAndRestoreTimer();

        initUI();
    }

    public void refreshFragment() {

        getSettingsAndRestoreTimer();

        initUI(); //?
    }

//    @Inject
//    KeepScreenOn keepScreenOn;

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

        //keepScreenOn.keep(generalSettings.bool[2]); //TODO Analyze DI chain
        //binding.getRoot().setKeepScreenOn(generalSettings.bool[2]);
//        if(generalSettings.bool[2]) {
//            activityContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        } else {
//            activityContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        }
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
        if(timelInSeconds != 0) {
            binding.minutes.setText(String.valueOf(
                    (int) timelInSeconds / 60));
            binding.seconds.setText(String.format("%02d",//TODO Check warning
                    (int) timelInSeconds % 60));
        } else {
            binding.minutes.setText(String.valueOf(0));
            binding.seconds.setText(String.format("%02d", 0));
        }
    }

    @Override
    void notifyUser(NotifyUser notifyUser) {
        notifyUser.vibrateAndPlaySound(generalSettings.bool[0],generalSettings.bool[1]);
    }
}