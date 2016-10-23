package com.bond.iampomodoro.presenter;

import android.content.Context;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.view.RepoInfoMainView;

import javax.inject.Inject;

//TODO Make Singleton
//public class Presenter extends Service {
public class Presenter implements PresenterInterface {
//public class Presenter {

    /** UNCOMMENT **/
    public Presenter() {
        App.getComponent().inject(this);
    }

    @Inject
    SettingsHelper settingsHelper;
    //SettingsHelper settingsHelper = new SettingsHelper();

    private RepoInfoMainView view;

    //public void onCreate(RepoInfoMainView view, Repository repository) {
//    public void onCreate(RepoInfoMainView view) {
//        ///App.getComponent().inject(this);
//        this.view = view;
//
//        settingsHelper = new SettingsHelper();
//    }

    public void saveSetings(Context context, SettingsObject settings) {

        settingsHelper.setSetings(context, settings);
    }

    public SettingsObject notifySettingsFragmentStart(Context context) {

        return settingsHelper.getSetings(context);
    }
}
