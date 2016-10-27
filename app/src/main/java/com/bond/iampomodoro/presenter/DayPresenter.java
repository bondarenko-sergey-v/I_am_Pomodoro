package com.bond.iampomodoro.presenter;

import android.view.View;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.jakewharton.rxbinding.view.RxView;

public class DayPresenter extends BasePresenter {

    private FragmentDayBinding binding;
    private String buttonsState = "Start";

    public void notifyDayFragmentStarts(FragmentDayBinding binding) {
        this.binding = binding;

        getSettings();

        initUI();
    }

    private void initUI() {
        showTime(workSessionMin,0);

        RxView.clicks(binding.startBtn)
                .skip(1)
                .subscribe(v -> { setTimerState(buttonsState);
          //          switch (binding.startBtn.getText().toString()) {
          //              case "Start":
          //                  timerStart();
          //                  binding.startBtn.setText(R.string.pause);
          //                  binding.resetBtn.setEnabled(true);
          //                  binding.resetBtn.setVisibility(View.VISIBLE);
          //                  break;
          //              case "Pause":
          //                  clearCompositeSubscription();
          //                  saveTimerSettings();
          //                  binding.startBtn.setText(R.string.resume);
          //                  break;
          //              case "Resume":
          //                  timerResume();
          //                  binding.startBtn.setText(R.string.pause);
          //                  break;
          //          }
                });

        RxView.clicks(binding.resetBtn)
                .skip(1)
                .subscribe(v -> { setTimerState("Reset");
         //           clearCompositeSubscription();
         //           showTime(workSessionMin,0);
         //           binding.startBtn.setText(R.string.start);
         //           binding.resetBtn.setEnabled(false);
         //           binding.resetBtn.setVisibility(View.INVISIBLE);
         //           isWorkTime = true;
         //           breaksCount = 0;
                });
    }

    public void setTimerState(String butonsState) {
        switch (butonsState) {
            case "Start":
                timerStart();
                binding.startBtn.setText(R.string.pause);
                binding.resetBtn.setEnabled(true);
                binding.resetBtn.setVisibility(View.VISIBLE);
                break;
         //   case "Pause":
         //       clearCompositeSubscription();
         //       saveTimerSettings();
         //       binding.startBtn.setText(R.string.resume);
         //       break;
            case "Resume":
                timerResume();
                binding.startBtn.setText(R.string.pause);
                break;
            case "Reset":
                clearCompositeSubscription();
                showTime(workSessionMin,0);
                binding.startBtn.setText(R.string.start);
                binding.resetBtn.setEnabled(false);
                binding.resetBtn.setVisibility(View.INVISIBLE);
                isWorkTime = true;
                breaksCount = 0;
                break;
    }


    @Override
    public void showTime(int minutes, int seconds) {
        binding.minutes.setText(String.valueOf(minutes));
        binding.seconds.setText(String.format("%02d", seconds)); //TODO Check warning
    }
}