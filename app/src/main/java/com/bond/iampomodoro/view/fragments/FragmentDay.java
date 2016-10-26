package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.bond.iampomodoro.presenter.Presenter;

import javax.inject.Inject;

public class FragmentDay extends Fragment{

    private TextView minutesTextView;
    private TextView secondsTextView;
    private Button startBtn;
    private Button resetBtn;

    @Inject
    Presenter presenter;

    private FragmentDayBinding binding;

    public static FragmentDay newInstance() {
        return new FragmentDay();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);
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

        presenter.notifyDayFragmentStarts(binding);
        //settingsPresenter.notifySettingsFragmentStarts(binding);
        //TODO SettingsPresenter.startNewFragment
    }
}