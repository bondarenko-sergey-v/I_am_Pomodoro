package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bond.iampomodoro.model.dto.UserSettingsObject;
import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.presenter.SettingsPresenter;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;
import com.bond.iampomodoro.view.MainActivity;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

import static rx.Observable.combineLatest;

public class FragmentSettings extends BaseFragment implements SettingsView {

    @Inject
    SettingsPresenter presenter;

    private FragmentSettingsBinding binding;

    private UserSettingsObject usrSet;

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onCreate(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && presenter != null) {
            presenter.onTabSelected();
        }
    }

    @Override
    protected Presenter getPresenter() {
        MainActivity.getActivityComponent().inject(this);
        return presenter;
    }

    @Override
    public Observable showSettings(UserSettingsObject usrSet) {
        this.usrSet = usrSet;

        Observable<Boolean[]> obs1 = initCheckBoxes();
        Observable<int[]> obs2 = initSeekbars();

        return Observable.combineLatest(obs1, obs2, (b, i) ->
                new UserSettingsObject(b, i[0], i[1], i[2], i[3]));
    }

    private Observable<Boolean[]> initCheckBoxes() {
        List<CheckBox> cbList = Arrays.asList( binding.cbxDaySound, binding.cbxDayVibration,
                binding.cbxDayKeepScreen, binding.cbxNightSound, binding.cbxNightVibration,
                binding.cbxNightKeepScreen, binding.cbxNightPictures);

        return Observable.from(cbList)
                .doOnNext(v -> v.setChecked(usrSet.bool[cbList.indexOf(v)])) //TODO Refactor index calculation
                .map(RxCompoundButton::checkedChanges)                         // with .count or something else
                .toList()
                .flatMap(v -> combineLatest(v, args -> {
                    List<Object> combine = new ArrayList<>();
                    Collections.addAll(combine, args);
                    return combine.toArray(new Boolean[combine.size()]);
                    }));
    }

    private Observable<int[]> initSeekbars() {
        Observable<Integer> sb1 = RxSeekBar.changes(binding.seekbarWorkSession)
                .map(v -> v + 25)
                .doOnSubscribe(() -> {
                    binding.textViewWorkSession.setText(String.valueOf(usrSet.workSession));
                    binding.seekbarWorkSession.setProgress(usrSet.workSession - 25);
                })
                .doOnNext(v -> binding.textViewWorkSession.setText(String.valueOf(v)));

        Observable<Integer> sb2 = RxSeekBar.changes(binding.seekbarBreak)
                .map(v -> v + 5)
                .doOnSubscribe(() -> {
                    binding.textViewBreak.setText(String.valueOf(usrSet.breakMin));
                    binding.seekbarBreak.setProgress(usrSet.breakMin - 5);
                })
                .doOnNext(v -> binding.textViewBreak.setText(String.valueOf(v)));

        Observable<Integer> sb3 = RxSeekBar.changes(binding.seekbarLongBreak)
                .map(v -> v + 15)
                .doOnSubscribe(() -> {
                    binding.textViewLongBreak.setText(String.valueOf(usrSet.longBreak));
                    binding.seekbarLongBreak.setProgress(usrSet.longBreak - 15);
                })
                .doOnNext(v -> binding.textViewLongBreak.setText(String.valueOf(v)));

        Observable<Integer> sb4 = RxSeekBar.changes(binding.seekbarSessionsBeforeLB)
                .map(v -> v + 3)
                .doOnSubscribe(() -> {
                    binding.textViewSessionsBeforeLB.setText(String.valueOf(usrSet.sessionsBeforeLB));
                    binding.seekbarSessionsBeforeLB.setProgress(usrSet.sessionsBeforeLB - 3);
                })
                .doOnNext(v -> binding.textViewSessionsBeforeLB.setText(String.valueOf(v)));

        return Observable.combineLatest(sb1, sb2, sb3, sb4, (a1, a2, a3, a4) ->
                new int[]{ a1, a2, a3, a4 })
                .debounce(200, TimeUnit.MILLISECONDS);
    }
}