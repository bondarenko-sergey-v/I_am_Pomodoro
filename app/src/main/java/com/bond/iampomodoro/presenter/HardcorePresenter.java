package com.bond.iampomodoro.presenter;

import android.view.View;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentHardcoreBinding;
import com.bond.iampomodoro.model.NotifyUser;
import com.jakewharton.rxbinding.view.RxView;

public class HardcorePresenter extends BasePresenter {

//    @Inject
//    //@Named("ActivityContext")
//    @ActivityContext
//    Context activityContext;

    private FragmentHardcoreBinding binding;

    public void notifyHardcoreFragmentStarts(FragmentHardcoreBinding binding) {
        this.binding = binding;

        getSettingsAndRestoreTimer();

        initUI();
    }

    public void refreshFragment() {

        getSettingsAndRestoreTimer();

        initUI(); //?
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

        keepScreenOn.keep(generalSettings.bool[5]);
        //binding.getRoot().setKeepScreenOn(generalSettings.bool[5]);
 //       if(generalSettings.bool[5]) {
 //           activityContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
 //       } else {
 //           activityContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
 //       }
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
    public void showTime(int timeInSeconds) {
        binding.minutes.setText(String.valueOf(
                (int) timeInSeconds / 60));
        binding.seconds.setText(String.format("%02d",//TODO Check warning
                (int) timeInSeconds % 60));
    }

    @Override
    void notifyUser(NotifyUser notifyUser) { //TODO Fix double-link
        notifyUser.vibrateAndPlaySound(generalSettings.bool[3],generalSettings.bool[4]);
    }
}