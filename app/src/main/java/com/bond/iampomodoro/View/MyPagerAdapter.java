package com.bond.iampomodoro.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
                fragment = FragmentHardcore.newInstance();
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