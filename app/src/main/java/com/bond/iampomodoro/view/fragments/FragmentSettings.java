package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class FragmentSettings extends Fragment {

    @Inject
    SettingsPresenter settingsPresenter;

    @Inject
    CompositeSubscription subscriptionList;

    private FragmentSettingsBinding binding;

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingsPresenter.notifySettingsFragmentStarts(binding);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        settingsPresenter.saveSettings();
    }
}