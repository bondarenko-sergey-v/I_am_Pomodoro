package com.bond.iampomodoro.view.fragments;

import android.support.v4.app.Fragment;

import com.bond.iampomodoro.presenter.Presenter;

public abstract class BaseFragment extends Fragment {

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

}

