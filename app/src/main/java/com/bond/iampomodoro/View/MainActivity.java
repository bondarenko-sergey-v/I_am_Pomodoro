package com.bond.iampomodoro.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bond.iampomodoro.R;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(getApplication());

        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // Set tabs
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat
                .getColor(getApplicationContext(), R.color.colorTabIndicator));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_weather_sunny_light));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_code_tags_light));
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

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
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
}