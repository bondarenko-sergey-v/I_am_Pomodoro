package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentNightBinding;
import com.bond.iampomodoro.presenter.NightPresenter;
import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.view.MainActivity;
import com.jakewharton.rxbinding.view.RxView;

import java.util.Calendar;

import javax.inject.Inject;

public class FragmentNight extends BaseFragment implements NightView {

    @Inject
    NightPresenter presenter;

    private FragmentNightBinding binding;
    private boolean isFragmentVisible;

    public static FragmentNight newInstance() {
        return new FragmentNight();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_night, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onCreate(this);

        if(isFragmentVisible) {
            presenter.onTabSelected();
        }

        initUI();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //presenter.onTabUnselected();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isFragmentVisible = isVisibleToUser;

        if(isVisibleToUser && presenter != null) {
            presenter.onTabSelected();
        }

        if(!isVisibleToUser && presenter != null) {
            //presenter.onTabUnselected();
        }
    }

    @Override
    protected Presenter getPresenter() {
        MainActivity.getActivityComponent().inject(this);
        return presenter;
    }

    @Override
    public void showTime(int timeInSeconds) {

        if(timeInSeconds != 0) {
            binding.minutes.setText(String.valueOf(
                    (int) timeInSeconds / 60));
            binding.seconds.setText(String.format("%02d",//TODO Check warning
                    (int) timeInSeconds % 60));
        } else {
            binding.minutes.setText(String.valueOf(0));
            binding.seconds.setText(String.format("%02d", 0));
        }
    }

    @Override
    public void showButtons(String buttonsState) {
        switch(buttonsState) {
            case "Start":
                binding.startBtn.setText(R.string.pause);
                binding.resetBtn.setEnabled(true);
                binding.resetBtn.setVisibility(View.VISIBLE);
                break;
            case "Pause":
                binding.startBtn.setText(R.string.start);
                binding.resetBtn.setEnabled(true);
                binding.resetBtn.setVisibility(View.VISIBLE);
                break;
            case "Reset":
                binding.startBtn.setText(R.string.start);
                binding.resetBtn.setEnabled(false);
                binding.resetBtn.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void showImage(boolean showImage, int imageNumber) {
        //TODO Realize imageNumber functionality
        //TODO Had been shown at break time
        if(showImage) {
            //Calendar c = Calendar.getInstance();
            int hour = Calendar.getInstance() //TODO Check is Calendar instance leaks?
                               .get(Calendar.HOUR_OF_DAY);

            if(hour >= 12 || hour <= 6) {
                binding.imageView.getDrawable().setColorFilter(Color.rgb(144,144,144)
                        , PorterDuff.Mode.MULTIPLY );
                binding.imageView.setVisibility(View.VISIBLE);
            }

        } else {
            binding.imageView.setVisibility(View.INVISIBLE);
        }
    }

    private void initUI() {

        RxView.clicks(binding.startBtn)
                .subscribe(v -> presenter.onStartButtonClick());

        RxView.clicks(binding.resetBtn)
                .subscribe(v -> presenter.onResetButtonClick());
    }
}