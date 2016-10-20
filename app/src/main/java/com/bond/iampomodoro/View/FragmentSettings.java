package com.bond.iampomodoro.View;

import android.databinding.DataBindingUtil;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bond.iampomodoro.Model.SettingsHelper;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

import static rx.Observable.combineLatest;

public class FragmentSettings extends Fragment {

    View view;

    Boolean[] boolSettings;
    Integer[] intSettings;
    //TODO Refactor SettingsObject class
    SettingsHelper.SettingsObject settings = new SettingsHelper.SettingsObject(boolSettings,intSettings);

    boolean firstVisible = false;

    FragmentSettingsBinding binding;
    ScreenHandler screenHandler = new ScreenHandler();

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO Change to Rx
        settings = SettingsHelper.getSettings(getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        binding.setScreenHandler(screenHandler);

        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitSeekbars();
        InitCheckBoxes();
        //TODO Presenter.startSettingsFragment
    }

    @Override
    public void onPause() {

        SettingsHelper.setSettings(getActivity(), settings);

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //TODO Unsibscribe subscriptions
        //if (checkBoxDayVibration1 != null) {
        //    checkBoxDayVibration1.unsubscribe();
        //}
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Save settings only when Fragment was really visible at least once
        if(isVisibleToUser) { firstVisible = true; }

        if(!isVisibleToUser && firstVisible) {
            SettingsHelper.setSettings(getActivity(), settings);
        }
    }

    private void InitCheckBoxes() {
        CheckBox[] cbList = { binding.cbxDaySound, binding.cbxDayVibration, binding.cbxDayKeepScreen,
                binding.cbxNightSound, binding.cbxNightVibration, binding.cbxNightKeepScreen,
                binding.cbxNightPictures};

        Observable.from(cbList)
                .map(RxCompoundButton::checkedChanges)
                .toList()
                .flatMap(v -> combineLatest(v, args -> {
                    List<Boolean> combine = new ArrayList<>();
                    for(Object c : args) {
                        combine.add((Boolean) c);
                    }
                    return combine;
                }))
                .map(v -> v.toArray(new Boolean[v.size()]))
                .subscribe(v -> settings.bool = v); //TODO Change to Rx
    }

    private void InitSeekbars() {
        Observable<Integer> sb1 = RxSeekBar.changes(binding.seekbarWorkSession)
                .map(v -> v + 25)
                .doOnSubscribe(() -> {
                    screenHandler.textViewWorkSession.set(String.valueOf(settings.intr[0]));
                    screenHandler.progressWorkSession.set(settings.intr[0] - 25);
                })
                .doOnNext(v -> screenHandler.textViewWorkSession.set(String.valueOf(v)));

        Observable<Integer> sb2 = RxSeekBar.changes(binding.seekbarBreak)
                .map(v -> v + 5)
                .doOnNext(v -> screenHandler.textViewBreak.set(String.valueOf(v)));

        Observable<Integer> sb3 = RxSeekBar.changes(binding.seekbarLongBreak)
                .map(v -> v + 15)
                .doOnNext(v -> screenHandler.textViewLongBreak.set(String.valueOf(v)));

        Observable<Integer> sb4 = RxSeekBar.changes(binding.seekbarSessionsBeforeLB)
                .map(v -> v + 3)
                .doOnNext(v -> screenHandler.textViewSessionsBeforeLB.set(String.valueOf(v)));

        Observable.combineLatest(sb1,sb2,sb3,sb4, (a1,a2,a3,a4) ->
                Arrays.asList(a1, a2, a3, a4))
                .map(v -> v.toArray(new Integer[v.size()]))
                .subscribe(v -> settings.intr = v); //TODO Change to Rx
    }

    public static class ScreenHandler {

        //TODO Try to shift to XML
        public ObservableField<String> textViewWorkSession = new ObservableField<>();

        public ObservableField<String> textViewBreak = new ObservableField<>();
        public ObservableField<String> textViewLongBreak = new ObservableField<>();
        public ObservableField<String> textViewSessionsBeforeLB = new ObservableField<>();
        public ObservableField<Integer> progressWorkSession = new ObservableField<>(0);

        public ObservableField<Integer> progressBreak = new ObservableField<>(0);
        public ObservableField<Integer> progressLongBreak = new ObservableField<>(0);
        public ObservableField<Integer> progressSessionsBeforeLB = new ObservableField<>(0);
        public ObservableBoolean DaySound = new ObservableBoolean();

        public ObservableBoolean DayVibration = new ObservableBoolean();
        public ObservableBoolean DayKeepScreen = new ObservableBoolean();
        public ObservableBoolean NightSound = new ObservableBoolean();
        public ObservableBoolean NightVibration = new ObservableBoolean();
        public ObservableBoolean NightKeepScreen = new ObservableBoolean();
        public ObservableBoolean NightPictures = new ObservableBoolean();
    }
}