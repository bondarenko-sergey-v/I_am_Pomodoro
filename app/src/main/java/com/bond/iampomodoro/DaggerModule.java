package com.bond.iampomodoro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.bond.iampomodoro.Model.SettingsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {

    private final Activity activity;

    public DaggerModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    //@Singleton
    public SettingsHelper settingsHelper() {
        return new SettingsHelper();
    }

    @Provides
    public SharedPreferences sharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @ApplicationContext
    Context context

 //   protected final Application application;

//    public AppModule(WeatherApp application) {
//        this.application = application;
//
//        Timber.plant(new Timber.DebugTree());
//    }
//
//    @Provides @Singleton public Geocoder proviceGeocoder() {
//        return new Geocoder(application);
//    }

//    @Provides
//    @Singleton
//    public Application provideApplication() {
//        return application;
//    }



//   @Provides @Singleton public WeatherApi provideForecastApi() {
//       RestAdapter.Builder b = new RestAdapter.Builder();

//       if (BuildConfig.DEBUG) {
//           b.setLogLevel(RestAdapter.LogLevel.FULL);
//       }

//       b.setRequestInterceptor(request -> {
//           request.addQueryParam("key", BuildConfig.FORECAST_API_KEY);
//           request.addQueryParam("format", "json");
//       });

//       RestAdapter restAdapter = b.setEndpoint(BuildConfig.FORECAST_API_URL).build();
//       return restAdapter.create(WeatherApi.class);
//   }

//   @Provides @Singleton public PlacesApi providePlacesApi() {
//       RestAdapter.Builder b = new RestAdapter.Builder();

//       if (BuildConfig.DEBUG) {
//           b.setLogLevel(RestAdapter.LogLevel.FULL);
//       }

//       b.setRequestInterceptor(request -> {
//           request.addQueryParam("key", BuildConfig.PLACES_API_KEY);
//           request.addQueryParam("sensor", "false");
//       });

//       RestAdapter restAdapter = b.setEndpoint(BuildConfig.PLACES_API_URL).build();
//       return restAdapter.create(PlacesApi.class);
//   }

//   @Provides @Singleton public Gson provideGson() {
//       return new GsonBuilder().create();
//   }

//   @Provides @Singleton @IOSched public Scheduler provideIoScheduler() {
//       return Schedulers.io();
//   }

//   @Provides @Singleton @UISched public Scheduler provideUiScheduler() {
//       return AndroidSchedulers.mainThread();
//   }
}

