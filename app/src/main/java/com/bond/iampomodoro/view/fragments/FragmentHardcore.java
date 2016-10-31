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
import com.bond.iampomodoro.databinding.FragmentHardcoreBinding;
import com.bond.iampomodoro.presenter.HardcorePresenter;

import javax.inject.Inject;

public class FragmentHardcore extends Fragment {

    @Inject
    HardcorePresenter hardcorePresenter;

    private FragmentHardcoreBinding binding;

    public static FragmentHardcore newInstance() {
        return new FragmentHardcore();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && hardcorePresenter != null) {
            hardcorePresenter.refreshFragment();
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hardcore, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hardcorePresenter.notifyHardcoreFragmentStarts(binding);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        hardcorePresenter.saveTimerSettings();
    }
}