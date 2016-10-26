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
import com.bond.iampomodoro.presenter.DayPresenter;

import javax.inject.Inject;

public class FragmentHardcore extends Fragment {

    @Inject
    DayPresenter presenter;

    private FragmentHardcoreBinding binding;

    public static FragmentHardcore newInstance() {
        return new FragmentHardcore();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);
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

        //TODO Change notification
        //presenter.notifyDayFragmentStarts(binding);
    }
}