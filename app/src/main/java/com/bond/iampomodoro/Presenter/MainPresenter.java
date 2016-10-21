package com.bond.iampomodoro.Presenter;

import android.content.Context;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.Model.SettingsHelper;
import com.bond.iampomodoro.Model.SettingsObject;
import com.bond.iampomodoro.View.RepoInfoMainView;

//TODO Make Singleton
//public class MainPresenter extends Service {
//public class MainPresenter implements Presenter {
public class MainPresenter {

    //@Inject SettingsHelper settingsHelper;
    SettingsHelper settingsHelper;

    private RepoInfoMainView view;

    //public void onCreate(RepoInfoMainView view, Repository repository) {
    public void onCreate(RepoInfoMainView view) {
        App.getComponent().inject(this);
        this.view = view;
        //this.repository = repository;

        settingsHelper = new SettingsHelper();
    }

    public void saveSetings(Context context, SettingsObject settings) {

        settingsHelper.setSetings(context, settings);
    }

    public SettingsObject notifySettingsFragmentStart(Context context) {

        return settingsHelper.getSetings(context);
    }
}
