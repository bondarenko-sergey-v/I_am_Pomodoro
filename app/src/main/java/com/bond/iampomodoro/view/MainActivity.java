package com.bond.iampomodoro.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.di.ActivityComponent;
import com.bond.iampomodoro.di.ActivityModule;
import com.bond.iampomodoro.di.annotations.ApplicationContext;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    @ApplicationContext
    Context appContext;

    private TabLayout tabLayout;
    private static ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent()
                .plus(new ActivityModule(this))
                .inject(this);                   //TODO Join

        activityComponent = App.getAppComponent()
                .plus(new ActivityModule(this)); //TODO Join if possible

   //     if (LeakCanary.isInAnalyzerProcess(this)) {
   //         return;
   //     }
   //     LeakCanary.install(getApplication());

        setContentView(R.layout.activity_main);

        setTabLayout();
    }

    private void setTabLayout() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat
                .getColor(appContext, R.color.colorTabIndicator));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_weather_sunny_light));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_code_tags_light2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_settings_light));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setLightTabs();
                        break;
                    default:
                        setDarkTabs();
                        break;
                }
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) { }
            @Override public void onTabReselected(TabLayout.Tab tab) { }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setLightTabs() {
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_weather_sunny_light);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_code_tags_light2);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_settings_light);
    }

    private void setDarkTabs() {
        tabLayout.setBackgroundColor(Color.BLACK);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_weather_sunny);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_code_tags2);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_settings);
    }

    public static ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}