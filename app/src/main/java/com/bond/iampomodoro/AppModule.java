package com.bond.iampomodoro;

import android.app.Application;

import com.bond.iampomodoro.Presenter.MainPresenter;
import com.bond.iampomodoro.View.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    protected final Application application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public MainPresenter mainPresenter() {
        return new MainPresenter();
    }
}







//    public AppModule(MainActivity mainActivity) {
//
//    }

    //private final Activity activity;
    //protected final Application application;

 //   public AppModule(Activity activity) {
 //       this.activity = activity;
 //   }



 //   @Provides
 //   public Geocoder provideGeocoder() {
 //       return new Geocoder(application);
 //   }
//
 //   @Provides
 //   Context applicationContext() {
 //      return application;
 //  }



  //  @Provides
  //  @Singleton
  //  public SettingsHelper settingsHelper() {
  //      return new SettingsHelper();
  //  }
//
  //  @Provides
  //  public SharedPreferences sharedPreferences() {
  //      return PreferenceManager.getDefaultSharedPreferences(applicationContext());
  //  }


