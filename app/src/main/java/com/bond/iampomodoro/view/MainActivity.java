package com.bond.iampomodoro.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.di.ActivityModule;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.presenter.DayPresenter;
import com.bond.iampomodoro.presenter.HardcorePresenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Context appContext;
    @Inject
    SettingsHelper settingsHelper;
    @Inject
    DayPresenter dayPresenter;
    @Inject
    HardcorePresenter hardcorePresenter;
    @Inject
    SettingsPresenter settingsPresenter;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent()
                .plus(new ActivityModule(this))
                .inject(this);

//        if (LeakCanary.isInAnalyzerProcess(this)) { //TODO Check memory leaks
//            return;
//        }
//        LeakCanary.install(getApplication());

        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat
                .getColor(appContext, R.color.colorTabIndicator));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_weather_sunny_light));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_code_tags_light));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_settings_light));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SettingsObject ob = settingsHelper.getSetings();
                switch (tab.getPosition()) {
                    case 0:
                        setLightTabs();
                        keepScreenOn(ob.bool[2]);
                        break;
                    default:
                        setDarkTabs();
                        keepScreenOn(ob.bool[5]);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        dayPresenter.saveTimerSettings();
                        System.out.println("Day - Save timer settings!");
                        break;
                    case 1:
                        hardcorePresenter.saveTimerSettings();
                        System.out.println("Hardcore - Save timer settings!");
                        break;
                    case 2:
                        settingsPresenter.saveSettings();
                        System.out.println("Settings - Save timer settings!");
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setLightTabs() {
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_weather_sunny_light);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_code_tags_light);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_settings_light);
    }

    private void setDarkTabs() {
        tabLayout.setBackgroundColor(Color.BLACK);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_weather_sunny);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_code_tags);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_settings);
    }
    private void keepScreenOn(boolean keepScreenOn) {
        if(keepScreenOn) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}