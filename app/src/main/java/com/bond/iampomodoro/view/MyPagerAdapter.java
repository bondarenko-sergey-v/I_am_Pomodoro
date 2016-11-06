package com.bond.iampomodoro.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bond.iampomodoro.view.fragments.FragmentDay;
import com.bond.iampomodoro.view.fragments.FragmentNight;
import com.bond.iampomodoro.view.fragments.FragmentSettings;

class MyPagerAdapter extends FragmentPagerAdapter {

    MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment;

        switch (pos) {
            case 0:
                fragment = FragmentDay.newInstance();
                break;
            case 1:
                fragment = FragmentNight.newInstance();
                break;
            default:
                fragment = FragmentSettings.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}