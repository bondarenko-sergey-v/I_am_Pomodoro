package com.bond.iampomodoro.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.AppComponent;
import com.bond.iampomodoro.R;
import com.squareup.leakcanary.LeakCanary;

public class MainActivity extends AppCompatActivity
        implements HasComponent<ActivityComponent> {

    private TabLayout tabLayout;

    private ActivityComponent component;

    //@Inject Geocoder geocoder;

    public ActivityComponent createComponent() {
        App app = (App) getApplication();
        ActivityComponent component = DaggerActivityComponent.builder()
                .appComponent(app.getComponent())
                .activityModule(new ActivityModule(this))
                .build();

        return component;
    }

    @Override
    public ActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(getApplication());

        this.component = createComponent();

  //      App app = (App) getApplication();
  //      app.getComponent().inject(this);


 //!!!!!        AppComponent appComponent = DaggerAppComponent.builder()
 //!!!!!                .appModule(new AppModule())
 //!!!!!                .build();


                 //   this.component = DaggerActivityComponent.builder()
    //           .appModule(new AppModule(this))
    //           .build();

        // Explicitly reference the application object since we don't want to match our own injector.
//        ObjectGraph appGraph = Injector.obtain(getApplication());
//        appGraph.inject(this);
//        activityGraph = appGraph.plus(new MainActivityModule(this));


  //      ActivityComponent component = DaggerActivityComponent.builder()
  //              .appComponent(app.getComponent())
  //              .activityModule(new ActivityModule(this))
  //              .build();

//        activityComponent().inject(this);



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