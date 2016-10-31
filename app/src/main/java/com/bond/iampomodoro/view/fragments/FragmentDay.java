package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.bond.iampomodoro.presenter.DayPresenter;

import javax.inject.Inject;

public class FragmentDay extends Fragment{

    @Inject
    DayPresenter dayPresenter;

    private static FragmentDayBinding binding;

    public static FragmentDay newInstance() {
        return new FragmentDay();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && dayPresenter != null) {
            dayPresenter.refreshFragment(); //TODO Make reinflation faster
            }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dayPresenter.notifyDayFragmentStarts(binding);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        dayPresenter.saveTimerSettings();
    }

    public static FragmentDayBinding getFragmentDayBinding() {
        return binding;
    }
}