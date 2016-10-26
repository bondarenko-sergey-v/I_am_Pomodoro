package com.bond.iampomodoro.presenter;

import android.view.View;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentHardcoreBinding;
import com.jakewharton.rxbinding.view.RxView;

public class HardcorePresenter extends BasePresenter {

    private FragmentHardcoreBinding binding;

    public HardcorePresenter() {
        App.getComponent().inject(this);
    }

    public void notifyHardcoreFragmentStarts(FragmentHardcoreBinding binding) {
        this.binding = binding;

        getSettings();

        initUI();
    }

    private void initUI() {
        showTime(workSessionMin,0);

        RxView.clicks(binding.startBtn)
                .skip(1)
                .subscribe(v -> {
                    switch (binding.startBtn.getText().toString()) {
                        case "Start":
                            timerStart();
                            binding.startBtn.setText(R.string.pause);
                            binding.resetBtn.setEnabled(true);
                            binding.resetBtn.setVisibility(View.VISIBLE);
                            break;
                        case "Pause":
                            clearCompositeSubscription();
                            binding.startBtn.setText(R.string.resume);
                            break;
                        case "Resume":
                            timerResume();
                            binding.startBtn.setText(R.string.pause);
                            break;
                    }
                });

        RxView.clicks(binding.resetBtn)
                .skip(1)
                .subscribe(v -> {
                    clearCompositeSubscription();
                    showTime(workSessionMin,0);
                    binding.startBtn.setText(R.string.start);
                    binding.resetBtn.setEnabled(false);
                    binding.resetBtn.setVisibility(View.INVISIBLE);
                    isWorkTime = true;
                    breaksCount = 0;
                });
    }

    @Override
    public void showTime(int minutes, int seconds) {
        binding.minutes.setText(String.valueOf(minutes));
        binding.seconds.setText(String.format("%02d", seconds)); //TODO Check warning
    }
}